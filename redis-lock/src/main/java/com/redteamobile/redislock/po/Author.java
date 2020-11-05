package com.redteamobile.redislock.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName Author
 * @Description TODO
 * @Author 郑迪文
 * @Date 2020/5/27 9:27 下午
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable {
    private Integer id;
    private String appId;
    private String modifyAuthor;
    private String namespaceName;
    private Timestamp createTime;
    private Timestamp updateTime;
}