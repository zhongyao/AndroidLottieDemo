package com.hongri.lottie.bean;

/**
 * @author zhongyao
 * @date 2018/11/25
 * 状态机
 */

public class RefreshState {
    public static final int RESET = 0x00;
    public static final int PULL_TO_REFRESH = 0x01;
    public static final int RELEASE_TO_REFRESH = 0x02;
    public static final int REFRESHING = 0x03;
    public static final int AUTO_REFRESHING = 0x04;
    public static final int REFRESHING_COMPLETE = 0x05;
}
