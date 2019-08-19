package com.ice.pattern.command;

/**
 * command子类，组合真正的handler实例
 * @author: ice
 * @create: 2019/4/2
 **/
public class HotCommand implements Command {
    private HotHandler hotHandler;

    public HotCommand(HotHandler hotHandler) {
        this.hotHandler = hotHandler;
    }

    @Override
    public void execute() {
        hotHandler.hotList();
    }
}
