package com.ice.pattern.command;

/**
 * command子类，组合真正的handler实例
 * @author: ice
 * @create: 2019/4/2
 **/
public class TopCommand implements Command {
    private TopHandler topHandler;

    public TopCommand(TopHandler topHandler) {
        this.topHandler = topHandler;
    }

    @Override
    public void execute() {
        topHandler.topList();
    }
}
