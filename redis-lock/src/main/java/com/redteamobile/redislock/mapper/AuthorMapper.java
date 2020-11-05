package com.redteamobile.redislock.mapper;

import com.redteamobile.redislock.po.Author;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhengdiwen
 */
@Mapper
@Repository
public interface AuthorMapper {

    /**
     * 根据appId查询到所有的namespace
     * @param appId
     * @return
     */
    @Select("select namespace_name from author where app_id = #{appId} ")
    List<String> getNamespaceNameByAppId(String appId);

    /**
     * 获取修改者的信息 根据appName
     * @param appId
     * @param namespaceName
     * @return
     */
    @Select("select * from author where app_id = #{appId} and namespace_name = #{namespaceName}")
    Author getAuthorByAppIdAndNameSpaceName(String appId, String namespaceName);

    /**
     * 更新修改者表中的修改者名称
     * @param name
     * @param appId
     * @param updateTime
     * @param namespaceName
     */
    @Update("update author set modify_author = #{name} , update_time = #{updateTime} where app_id = #{appId} and namespace_name = #{namespaceName}")
    void updateAuthorByModifyName(String name, String appId, Timestamp updateTime, String namespaceName);

    /**
     * 插入修改者
     * @param author
     */
    @Insert("insert into author (modify_author,app_id,namespace_name,create_time,update_time) values (#{modifyAuthor},#{appId},#{namespaceName},#{createTime},#{updateTime})")
    void insertAuthor(Author author);

    /**
     * 删除
     * @param appId
     * @param namespaceName
     */
    @Delete("delete from author where app_id = #{appId} and namespace_name = #{namespaceName} ")
    void deleteAuthor(String appId,String namespaceName);

}
