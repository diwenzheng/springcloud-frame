package com.redteamobile.redislock.common;

import com.redteamobile.redislock.common.base.BaseResponse;
import com.redteamobile.redislock.common.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RedisLockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockService.class);

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 锁定订单 执行指定方法
     *
     * @param orderId    orderId
     * @param waitSecond 获取锁最大等待时间
     * @param lockSecond 锁定时间
     * @param action     要执行的方法
     * @return
     */
    public boolean lockOrderRun(String orderId, int waitSecond, int lockSecond, Action action) {
        String value = UUID.randomUUID().toString();
        return lockRun(getOrderLockKey(orderId), value, waitSecond, lockSecond, action);
    }

    private String getOrderLockKey(String orderId){
        return "ORDER_LOCK_" + orderId;
    }
    private String getDeviceLockKey(String eid){
        return "DEVICE_LOCK_" + eid;
    }

    /**
     * 尝试锁定设备
     *
     * @param eid        eid
     * @param uuidValue  随机uuid
     * @param waitSecond 获取锁最大等待时间
     * @param lockSecond 锁定时间
     * @return
     */
    public boolean lockDevice(String eid, String uuidValue, int waitSecond, int lockSecond) {
        boolean lockDeviceResult = redisUtils.tryGetRedisLock(getDeviceLockKey(eid), uuidValue, waitSecond, lockSecond);
        LOGGER.info("start lockDevice eid:[{}], value:[{}], result:[{}]", eid, uuidValue, lockDeviceResult);
        return lockDeviceResult;
    }

    /**
     * 释放设备锁
     *
     * @param eid       eid
     * @param uuidValue 随机uuid
     * @return
     */
    public boolean releaseDevice(String eid, String uuidValue) {
        boolean releaseLock = redisUtils.releaseLock(getDeviceLockKey(eid), uuidValue);
        LOGGER.info("end releaseLock eid:[{}], value:[{}], result:[{}]", eid, uuidValue, releaseLock);
        return releaseLock;
    }

    /**
     * 锁定eid 执行指定方法
     *
     * @param eid        eid
     * @param waitSecond 获取锁最大等待时间
     * @param lockSecond 锁定时间
     * @param action     要执行的方法
     * @return
     */
    public boolean lockDeviceRun(String eid, int waitSecond, int lockSecond, Action action) {
        String value = UUID.randomUUID().toString();
        return lockRun(getDeviceLockKey(eid), value, waitSecond, lockSecond, action);
    }

    /**
     * 先获取全局锁,再执行指定方法
     *
     * @param key        锁key
     * @param waitSecond 获取锁最大等待时间
     * @param lockSecond 锁定时间
     * @param action     要执行的方法
     * @return BaseResponse
     */
    public Object lockAspectRun(String key, int waitSecond, int lockSecond, Action action) {
        String value = UUID.randomUUID().toString();
        LOGGER.info("start lockAspectRun key:[{}], value:[{}]", key, value);
        boolean getRedisLockResult = redisUtils.tryGetRedisLock(key, value, waitSecond, lockSecond);
        if (!getRedisLockResult) {
            return BaseResponse.addError(ErrorCodeEnum.OPERATE_FAILED, "请勿频繁操作");
        }
        RuntimeException error = null;
        try {
           return action.call();
        } catch (RuntimeException e) {
            error = e;
        } finally {
            boolean releaseLockResult = redisUtils.releaseLock(key, value);
            LOGGER.info("end lockAspectRun key:[{}], value:[{}], result:[{}]", key, value, releaseLockResult);
        }
        throw error;
    }



    /**
     * 先获取全局锁,再执行指定方法
     *
     * @param key        锁key
     * @param value      锁value
     * @param waitSecond 获取锁最大等待时间
     * @param lockSecond 锁定时间
     * @param action     要执行的方法
     * @return
     */
    public boolean lockRun(String key, String value, int waitSecond, int lockSecond, Action action) {
        LOGGER.info("start lockRun key:[{}], value:[{}]", key, value);
        boolean getRedisLockResult = redisUtils.tryGetRedisLock(key, value, waitSecond, lockSecond);
        if (!getRedisLockResult) return false;
        RuntimeException error = null;
        try {
            action.call();
        } catch (RuntimeException e) {
            error = e;
        } finally {
            boolean releaseLockResult = redisUtils.releaseLock(key, value);
            LOGGER.info("end lockRun key:[{}], value:[{}], result:[{}]", key, value, releaseLockResult);
        }
        if (error != null) {
            throw error;
        }
        return true;
    }

}

