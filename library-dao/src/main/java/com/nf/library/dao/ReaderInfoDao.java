package com.nf.library.dao;

import com.nf.library.entity.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 读者信息的相关操作
 * @author Sam
 */
public interface ReaderInfoDao {
    /**
     * 根据读者id查询信息
     * @param id
     * @return
     */
    ReaderInfo getById(Integer id);

    /**
     * 根据具体的图书信息查询信息
     * @param readerInfo
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ReaderInfo> getAll(@Param("reader") ReaderInfo readerInfo,
                            @Param("pageNum")int pageNum,
                            @Param("pageSize")int pageSize);

    /**
     * 批量删除
     * @param id
     */
    void readerInfoBatchDelete(Integer[] id);

    /**
     * 根据id值单个删除
     * @param id
     */
    void readerInfoDelete(Integer id);

    /**
     * 修改用户数据
     * @param readerInfo
     */
    void readerInfoUpdate(ReaderInfo readerInfo);

    /**
     * 增加数据
     * @param readerInfo
     */
    void readerInfoInsert(ReaderInfo readerInfo);

    /**
     * 批量添加数据
     * @param readerInfos
     */
    void readerInfoBatchInsert(List<ReaderInfo> readerInfos);
}
