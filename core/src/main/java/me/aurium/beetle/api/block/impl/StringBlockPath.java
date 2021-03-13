package me.aurium.beetle.api.block.impl;

import me.aurium.beetle.api.block.path.Block;
import me.aurium.beetle.api.block.path.BlockPath;

import java.util.LinkedList;
import java.util.List;

/**
 * A block path that represents itself as a string
 */
public class StringBlockPath implements BlockPath {

    public final static String COMMON_SPLITTER = "-";

    private final BlockPath root;
    private final BlockPath parent;
    private final LinkedList<Block> blocks;
    private final boolean isSevered;
    private final String splitter;

    public StringBlockPath(BlockPath root, BlockPath parent, LinkedList<Block> blocks, boolean isSevered, String splitter) {
        this.root = root;
        this.parent = parent;
        this.blocks = blocks;
        this.isSevered = isSevered;
        this.splitter = splitter;
    }

    public StringBlockPath(LinkedList<Block> list, boolean isSevered, String splitter) {
        this.root = this;
        this.parent = this;
        this.blocks = list;
        this.isSevered = isSevered;
        this.splitter = splitter;
    }

    public StringBlockPath(String splitter) {
        this.root = this;
        this.parent = this;
        this.blocks = new LinkedList<>();
        this.isSevered = true;
        this.splitter = splitter;
    }

    @Override
    public boolean isSevered() {
        return isSevered;
    }

    @Override
    public boolean endsWith(Block path) {
        return blocks.getLast().equals(path);
    }

    @Override
    public boolean startsWith(Block path) {
        return blocks.getFirst().equals(path);
    }

    @Override
    public BlockPath getRoot() {
        return root;
    }

    @Override
    public BlockPath getParent() {
        return parent;
    }

    @Override
    public BlockPath resolve(BlockPath path) {

        LinkedList<Block> pathblocks = this.blocks;

        for (Block block : path.getAllBlocks()) {
            pathblocks.addLast(block);
        }

        return new StringBlockPath(root,this,pathblocks,false,this.splitter);
    }

    @Override
    public BlockPath resolve(Block block) {

        LinkedList<Block> pathBlocks = this.blocks;

        pathBlocks.addLast(block);

        return new StringBlockPath(root,this,pathBlocks,false,this.splitter);
    }

    @Override
    public List<Block> getAllBlocks() {
        return this.blocks;
    }

    @Override
    public String toString() {

        StringBuilder base = new StringBuilder();

        for (Block block : this.blocks) {
            base.append(splitter).append(block.getIdentifier());
        }


        //TODO: fix this:
        return base.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringBlockPath that = (StringBlockPath) o;
        return blocks.equals(that.blocks);
    }

    @Override
    public int hashCode() {
        return blocks.hashCode();
    }


    public static BlockPath of(String string, String splitter) {
        String[] subparts = string.split(splitter);

        return of(subparts,splitter);
    }

    public static BlockPath of(String[] commandArguments, String splitter) {
        LinkedList<Block> blocklist = new LinkedList<>();

        for (String s : commandArguments) {
            blocklist.addLast(StringBlock.of(s));
        }

        return new StringBlockPath(blocklist,commandArguments.length > 1,splitter);
    }

    public static BlockPath of(Block block, String splitter) {
        LinkedList<Block> send = new LinkedList<>();
        send.addLast(block);
        return new StringBlockPath(send,true,splitter);
    }

    public static BlockPath of(String string) {
        return of(string,COMMON_SPLITTER);
    }

    public static BlockPath of(String[] commandArguments) {
        return of(commandArguments,COMMON_SPLITTER);
    }

    public static BlockPath of(Block block) {
        return of(block,COMMON_SPLITTER);
    }
}