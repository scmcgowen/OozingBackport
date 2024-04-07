package com.herrkatze.oozingbackport;

import com.herrkatze.oozingbackport.lists.MobEffectList;
import com.herrkatze.oozingbackport.lists.PotionList;
import com.mojang.logging.LogUtils;
import net.minecraft.server.commands.SummonCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(OozingBackport.MODID)
public class OozingBackport
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "oozing_backport";
    // Directly reference a slf4j logger
    static final Logger LOGGER = LogUtils.getLogger();


    public OozingBackport()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        MobEffectList.MOB_EFFECTS.register(modEventBus);
        PotionList.POTION_LIST.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        // Register ourselves for server and other game events we are interested in

        MinecraftForge.EVENT_BUS.register(this);
        int id = 0;
    }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),Ingredient.of(Items.SLIME_BLOCK),PotionUtils.setPotion(new ItemStack(Items.POTION),PotionList.OOZING_POTION.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), Potions.AWKWARD)),Ingredient.of(Items.SLIME_BLOCK),PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION),PotionList.OOZING_POTION.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), Potions.AWKWARD)),Ingredient.of(Items.SLIME_BLOCK),PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION),PotionList.OOZING_POTION.get()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE)
    public static class DeathEventSubscriber
    {
        @SubscribeEvent
        public static void onLivingDeath(LivingDeathEvent event){
            LivingEntity entity = event.getEntity();
            if (!(entity instanceof Slime) && !(entity instanceof Player) && entity.hasEffect(MobEffectList.OOZING.get())) {
                LOGGER.debug(entity.toString());
                Vec3 pos = entity.getPosition(1.0f);
                Level level = event.getEntity().level();
                for (int i = 0; i <2 ; i++) {
                    Slime slime = new Slime(EntityType.SLIME,level);
                    slime.moveTo(pos);
                    slime.setSize(2,true);
                    level.addFreshEntity(slime);
                }

            }
        }
    }

}

