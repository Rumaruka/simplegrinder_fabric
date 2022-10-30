package com.rumaruka.simplegrinder.common.tile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rumaruka.simplegrinder.common.recipe.AbstractGrinderRecipe;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.SharedConstants;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.*;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCoalGrinderBE extends LockableContainerBlockEntity implements SidedInventory, RecipeUnlocker, RecipeInputProvider {
    protected static final int field_31286 = 0;
    protected static final int field_31287 = 1;
    protected static final int field_31288 = 2;
    public static final int field_31289 = 0;
    private static final int[] TOP_SLOTS = new int[]{0};
    private static final int[] BOTTOM_SLOTS = new int[]{2, 1};
    private static final int[] SIDE_SLOTS = new int[]{1};
    public static final int field_31290 = 1;
    public static final int field_31291 = 2;
    public static final int field_31292 = 3;
    public static final int field_31293 = 4;
    public static final int field_31294 = 200;
    public static final int field_31295 = 2;
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    int burnTime;
    int fuelTime;
    int cookTime;
    int cookTimeTotal;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            switch (index) {
                case 0: {
                    return AbstractCoalGrinderBE.this.burnTime;
                }
                case 1: {
                    return AbstractCoalGrinderBE.this.fuelTime;
                }
                case 2: {
                    return AbstractCoalGrinderBE.this.cookTime;
                }
                case 3: {
                    return AbstractCoalGrinderBE.this.cookTimeTotal;
                }
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: {
                    AbstractCoalGrinderBE.this.burnTime = value;
                    break;
                }
                case 1: {
                    AbstractCoalGrinderBE.this.fuelTime = value;
                    break;
                }
                case 2: {
                    AbstractCoalGrinderBE.this.cookTime = value;
                    break;
                }
                case 3: {
                    AbstractCoalGrinderBE.this.cookTimeTotal = value;
                    break;
                }
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };
    private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap();
    private final RecipeType<? extends AbstractGrinderRecipe> recipeType;

    protected AbstractCoalGrinderBE(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, RecipeType<? extends AbstractGrinderRecipe> recipeType) {
        super(blockEntityType, pos, state);
        this.recipeType = recipeType;
    }

    public static Map<Item, Integer> createFuelTimeMap() {
        LinkedHashMap<Item, Integer> map = Maps.newLinkedHashMap();
        AbstractCoalGrinderBE.addFuel(map, Items.LAVA_BUCKET, 20000);
        AbstractCoalGrinderBE.addFuel(map, Blocks.COAL_BLOCK, 16000);
        AbstractCoalGrinderBE.addFuel(map, Items.BLAZE_ROD, 2400);
        AbstractCoalGrinderBE.addFuel(map, Items.COAL, 1600);
        AbstractCoalGrinderBE.addFuel(map, Items.CHARCOAL, 1600);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.LOGS, 300);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.PLANKS, 300);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.WOODEN_STAIRS, 300);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.WOODEN_SLABS, 150);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.WOODEN_TRAPDOORS, 300);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.WOODEN_PRESSURE_PLATES, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.OAK_FENCE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.BIRCH_FENCE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.SPRUCE_FENCE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.JUNGLE_FENCE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.DARK_OAK_FENCE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.ACACIA_FENCE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.OAK_FENCE_GATE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.BIRCH_FENCE_GATE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.SPRUCE_FENCE_GATE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.JUNGLE_FENCE_GATE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.DARK_OAK_FENCE_GATE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.ACACIA_FENCE_GATE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.NOTE_BLOCK, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.BOOKSHELF, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.LECTERN, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.JUKEBOX, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.CHEST, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.TRAPPED_CHEST, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.CRAFTING_TABLE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.DAYLIGHT_DETECTOR, 300);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.BANNERS, 300);
        AbstractCoalGrinderBE.addFuel(map, Items.BOW, 300);
        AbstractCoalGrinderBE.addFuel(map, Items.FISHING_ROD, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.LADDER, 300);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.SIGNS, 200);
        AbstractCoalGrinderBE.addFuel(map, Items.WOODEN_SHOVEL, 200);
        AbstractCoalGrinderBE.addFuel(map, Items.WOODEN_SWORD, 200);
        AbstractCoalGrinderBE.addFuel(map, Items.WOODEN_HOE, 200);
        AbstractCoalGrinderBE.addFuel(map, Items.WOODEN_AXE, 200);
        AbstractCoalGrinderBE.addFuel(map, Items.WOODEN_PICKAXE, 200);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.WOODEN_DOORS, 200);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.BOATS, 1200);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.WOOL, 100);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.WOODEN_BUTTONS, 100);
        AbstractCoalGrinderBE.addFuel(map, Items.STICK, 100);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.SAPLINGS, 100);
        AbstractCoalGrinderBE.addFuel(map, Items.BOWL, 100);
        AbstractCoalGrinderBE.addFuel(map, ItemTags.SIGNS, 67);
        AbstractCoalGrinderBE.addFuel(map, Blocks.DRIED_KELP_BLOCK, 4001);
        AbstractCoalGrinderBE.addFuel(map, Items.CROSSBOW, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.BAMBOO, 50);
        AbstractCoalGrinderBE.addFuel(map, Blocks.DEAD_BUSH, 100);
        AbstractCoalGrinderBE.addFuel(map, Blocks.SCAFFOLDING, 400);
        AbstractCoalGrinderBE.addFuel(map, Blocks.LOOM, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.BARREL, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.CARTOGRAPHY_TABLE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.FLETCHING_TABLE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.SMITHING_TABLE, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.COMPOSTER, 300);
        AbstractCoalGrinderBE.addFuel(map, Blocks.AZALEA, 100);
        AbstractCoalGrinderBE.addFuel(map, Blocks.FLOWERING_AZALEA, 100);
        return map;
    }

    /**
     * {@return whether the provided {@code item} is in the {@link
     * net.minecraft.tag.ItemTags#NON_FLAMMABLE_WOOD non_flammable_wood} tag}
     */
    private static boolean isNonFlammableWood(Item item) {
        return item.getRegistryEntry().isIn(ItemTags.NON_FLAMMABLE_WOOD);
    }

    private static void addFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
        for (RegistryEntry<Item> registryEntry : Registry.ITEM.iterateEntries(tag)) {
            if (AbstractCoalGrinderBE.isNonFlammableWood(registryEntry.value())) continue;
            fuelTimes.put(registryEntry.value(), fuelTime);
        }
    }

    private static void addFuel(Map<Item, Integer> fuelTimes, ItemConvertible item, int fuelTime) {
        Item item2 = item.asItem();
        if (AbstractCoalGrinderBE.isNonFlammableWood(item2)) {
            if (SharedConstants.isDevelopment) {
                throw Util.throwOrPause(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item2.getName(null).getString() + " a furnace fuel. That will not work!"));
            }
            return;
        }
        fuelTimes.put(item2, fuelTime);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory);
        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.cookTimeTotal = nbt.getShort("CookTimeTotal");
        this.fuelTime = this.getFuelTime(this.inventory.get(1));
        NbtCompound nbtCompound = nbt.getCompound("RecipesUsed");
        for (String string : nbtCompound.getKeys()) {
            this.recipesUsed.put(new Identifier(string), nbtCompound.getInt(string));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("BurnTime", (short) this.burnTime);
        nbt.putShort("CookTime", (short) this.cookTime);
        nbt.putShort("CookTimeTotal", (short) this.cookTimeTotal);
        Inventories.writeNbt(nbt, this.inventory);
        NbtCompound nbtCompound = new NbtCompound();
        this.recipesUsed.forEach((identifier, count) -> nbtCompound.putInt(identifier.toString(), (int) count));
        nbt.put("RecipesUsed", nbtCompound);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AbstractCoalGrinderBE blockEntity) {
        boolean bl = blockEntity.isBurning();
        boolean bl2 = false;
        if (blockEntity.isBurning()) {
            --blockEntity.burnTime;
        }
        ItemStack itemStack = blockEntity.inventory.get(1);
        if (blockEntity.isBurning() || !itemStack.isEmpty() && !blockEntity.inventory.get(0).isEmpty()) {
            Recipe recipe = world.getRecipeManager().getFirstMatch(blockEntity.recipeType, blockEntity, world).orElse(null);
            int i = blockEntity.getMaxCountPerStack();
            if (!blockEntity.isBurning() && AbstractCoalGrinderBE.canAcceptRecipeOutput(recipe, blockEntity.inventory, i)) {
                blockEntity.fuelTime = blockEntity.burnTime = blockEntity.getFuelTime(itemStack);
                if (blockEntity.isBurning()) {
                    bl2 = true;
                    if (!itemStack.isEmpty()) {
                        Item item = itemStack.getItem();
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            Item item2 = item.getRecipeRemainder();
                            blockEntity.inventory.set(1, item2 == null ? ItemStack.EMPTY : new ItemStack(item2));
                        }
                    }
                }
            }
            if (blockEntity.isBurning() && AbstractCoalGrinderBE.canAcceptRecipeOutput(recipe, blockEntity.inventory, i)) {
                ++blockEntity.cookTime;
                if (blockEntity.cookTime == blockEntity.cookTimeTotal) {
                    blockEntity.cookTime = 0;
                    blockEntity.cookTimeTotal = AbstractCoalGrinderBE.getCookTime(world, blockEntity.recipeType, blockEntity);
                    if (AbstractCoalGrinderBE.craftRecipe(recipe, blockEntity.inventory, i)) {
                        blockEntity.setLastRecipe(recipe);
                    }
                    bl2 = true;
                }
            } else {
                blockEntity.cookTime = 0;
            }
        } else if (!blockEntity.isBurning() && blockEntity.cookTime > 0) {
            blockEntity.cookTime = MathHelper.clamp(blockEntity.cookTime - 2, 0, blockEntity.cookTimeTotal);
        }
        if (bl != blockEntity.isBurning()) {
            bl2 = true;
            state = (BlockState) state.with(AbstractFurnaceBlock.LIT, blockEntity.isBurning());
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
        }
        if (bl2) {
            AbstractCoalGrinderBE.markDirty(world, pos, state);
        }
    }

    private static boolean canAcceptRecipeOutput(@Nullable Recipe<?> recipe, DefaultedList<ItemStack> slots, int count) {
        if (slots.get(0).isEmpty() || recipe == null) {
            return false;
        }
        ItemStack itemStack = recipe.getOutput();
        if (itemStack.isEmpty()) {
            return false;
        }
        ItemStack itemStack2 = slots.get(2);
        if (itemStack2.isEmpty()) {
            return true;
        }
        if (!itemStack2.isItemEqualIgnoreDamage(itemStack)) {
            return false;
        }
        if (itemStack2.getCount() < count && itemStack2.getCount() < itemStack2.getMaxCount()) {
            return true;
        }
        return itemStack2.getCount() < itemStack.getMaxCount();
    }

    private static boolean craftRecipe(@Nullable Recipe<?> recipe, DefaultedList<ItemStack> slots, int count) {
        if (recipe == null || !AbstractCoalGrinderBE.canAcceptRecipeOutput(recipe, slots, count)) {
            return false;
        }
        ItemStack itemStack = slots.get(0);
        ItemStack itemStack2 = recipe.getOutput();
        ItemStack itemStack3 = slots.get(2);
        if (itemStack3.isEmpty()) {
            slots.set(2, itemStack2.copy());
        } else if (itemStack3.isOf(itemStack2.getItem())) {
            itemStack3.increment(1);
        }
        if (itemStack.isOf(Blocks.WET_SPONGE.asItem()) && !slots.get(1).isEmpty() && slots.get(1).isOf(Items.BUCKET)) {
            slots.set(1, new ItemStack(Items.WATER_BUCKET));
        }
        itemStack.decrement(1);
        return true;
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        }
        Item item = fuel.getItem();
        return AbstractCoalGrinderBE.createFuelTimeMap().getOrDefault(item, 0);
    }

    private static int getCookTime(World world, RecipeType<? extends AbstractGrinderRecipe> recipeType, Inventory inventory) {
        return world.getRecipeManager().getFirstMatch(recipeType, inventory, world).map(AbstractGrinderRecipe::getCookTime).orElse(200);
    }

    public static boolean canUseAsFuel(ItemStack stack) {
        return AbstractCoalGrinderBE.createFuelTimeMap().containsKey(stack.getItem());
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return BOTTOM_SLOTS;
        }
        if (side == Direction.UP) {
            return TOP_SLOTS;
        }
        return SIDE_SLOTS;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        if (dir == Direction.DOWN && slot == 1) {
            return stack.isOf(Items.WATER_BUCKET) || stack.isOf(Items.BUCKET);
        }
        return true;
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.inventory) {
            if (itemStack.isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(slot);
        boolean bl = !stack.isEmpty() && stack.isItemEqualIgnoreDamage(itemStack) && ItemStack.areNbtEqual(stack, itemStack);
        this.inventory.set(slot, stack);
        if (stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }
        if (slot == 0 && !bl) {
            this.cookTimeTotal = AbstractCoalGrinderBE.getCookTime(this.world, this.recipeType, this);
            this.cookTime = 0;
            this.markDirty();
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        }
        return player.squaredDistanceTo((double) this.pos.getX() + 0.5, (double) this.pos.getY() + 0.5, (double) this.pos.getZ() + 0.5) <= 64.0;
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 2) {
            return false;
        }
        if (slot == 1) {
            ItemStack itemStack = this.inventory.get(1);
            return AbstractCoalGrinderBE.canUseAsFuel(stack) || stack.isOf(Items.BUCKET) && !itemStack.isOf(Items.BUCKET);
        }
        return true;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    @Override
    public void setLastRecipe(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            Identifier identifier = recipe.getId();
            this.recipesUsed.addTo(identifier, 1);
        }
    }

    @Override
    @Nullable
    public Recipe<?> getLastRecipe() {
        return null;
    }

    @Override
    public void unlockLastRecipe(PlayerEntity player) {
    }

    public void dropExperienceForRecipesUsed(ServerPlayerEntity player) {
        List<Recipe<?>> list = this.getRecipesUsedAndDropExperience(player.getWorld(), player.getPos());
        player.unlockRecipes(list);
        this.recipesUsed.clear();
    }

    public List<Recipe<?>> getRecipesUsedAndDropExperience(ServerWorld world, Vec3d pos) {
        ArrayList<Recipe<?>> list = Lists.newArrayList();
        for (Object2IntMap.Entry entry : this.recipesUsed.object2IntEntrySet()) {
            world.getRecipeManager().get((Identifier) entry.getKey()).ifPresent(recipe -> {
                list.add((Recipe<?>) recipe);
                AbstractCoalGrinderBE.dropExperience(world, pos, entry.getIntValue(), ((AbstractGrinderRecipe) recipe).getExperience());
            });
        }
        return list;
    }

    private static void dropExperience(ServerWorld world, Vec3d pos, int multiplier, float experience) {
        int i = MathHelper.floor((float) multiplier * experience);
        float f = MathHelper.fractionalPart((float) multiplier * experience);
        if (f != 0.0f && Math.random() < (double) f) {
            ++i;
        }
        ExperienceOrbEntity.spawn(world, pos, i);
    }

    @Override
    public void provideRecipeInputs(RecipeMatcher finder) {
        for (ItemStack itemStack : this.inventory) {
            finder.addInput(itemStack);
        }
    }
}
