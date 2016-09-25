package com.site.repository;


import com.site.model.sign.SignRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wang0 on 2016/9/13.
 */
public interface SignRecordsRepo extends JpaRepository<SignRecords,Long> {

    @Query("select s from SignRecords s where s.comeTime>?1 and s.leaveTime<?2 order by s.name")
    List<SignRecords> findAllOrder(Timestamp starttime, Timestamp endtime);

    @Query("select sum(s.totalMill) from SignRecords s where s.name =?1 and s.comeTime>?2 and s.leaveTime<?3")
    Long queryByNameTime(String name, Timestamp starttime, Timestamp endtime);

    @Query("select s from SignRecords s where s.name =?1 and s.comeTime>?2 and s.leaveTime<?3")
    List<SignRecords> queryByNameTimeDetail(String name, Timestamp starttime, Timestamp endtime);

    @Query("select s from SignRecords s where s.comeTime>?1 and s.leaveTime<?2 order by s.name")
    List<SignRecords> queryByTimeDetailOrder(Timestamp starttime, Timestamp endtime);

    @Query("select s from SignRecords s where s.comeTime>?1 and s.leaveTime<?2 and s.name in (select m.name from Member m where m.grade = ?3) order by s.name")
    List<SignRecords> queryByGradeTimeDetail(Timestamp starttime, Timestamp endtime, Integer grade);

    @Query("select sum(s.totalMill) from SignRecords s where s.name=?1 and s.comeTime>?2 and s.leaveTime<?3")
    long gettoday(String name, Timestamp start, Timestamp end);

    @Query("select s.comeTime from SignRecords s where s.id=?1")
    Timestamp selectComeTime(Long id);

    @Modifying
    @Transactional
    @Query("update SignRecords s set s.leaveTime=?1,s.totalMill=?2,s.strTime=?3 where s.id=?4")
    int setSendEnd(Timestamp leaveTime, Long total_mill, String str_time, Long id);
//    /**
//     * 成功会返回数字1
//     * @param leaveTime
//     * @param id
//     * @return
//     */

    @Query("select s from SignRecords s where s.name=?1 order by id desc")
    List<SignRecords> selectDesc(String name);


}
