package com.yao.rui.demo328.utils;

/**
 * 双击退出逻辑
 * Created by Rny on 2017/3/29.
 */

public abstract class FinishLogic {
    private long clickTime = 0;
    private long doubleTime = 2000;

    public FinishLogic() {
    }

    public FinishLogic(long doubleTime) {
        super();
        this.doubleTime = doubleTime;
    }

    public final void onKeyBack() {
        long now = System.currentTimeMillis();
        if (Math.abs(now - clickTime) > doubleTime) {
            touchAgain();
            clickTime=now;
        } else {
            onFinsih();
        }
    }
    //再按一次
    protected abstract void touchAgain();
    //结束
    protected abstract void onFinsih();


}
