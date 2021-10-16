package com.daniel.feather_trampling.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin extends Block {

    public FarmlandBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FarmlandBlock;setToDirt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"), method="onLandedUpon", cancellable = true)
    public void checkFeatherFallingOnLanding(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo info) {
        if (entity != null) {
            if (EnchantmentHelper.getEquipmentLevel(Enchantments.FEATHER_FALLING, (LivingEntity) entity) > 0) {
                if (FabricLoader.getInstance().isDevelopmentEnvironment() && entity instanceof PlayerEntity) {
                    ((PlayerEntity) entity).sendMessage(new LiteralText("[Debug] Prevented trampling"), false);
                }
                super.onLandedUpon(world, state, pos, entity, fallDistance);
                info.cancel();
            }
        }
    }
}
