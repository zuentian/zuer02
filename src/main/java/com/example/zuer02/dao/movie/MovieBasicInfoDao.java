package com.example.zuer02.dao.movie;

import com.example.zuer02.entity.movie.MovieBasicInfo;
import com.example.zuer02.entity.movie.MovieInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovieBasicInfoDao {
    int insertMovieBasicInfo(MovieBasicInfo movieBasicInfo);

    List<MovieInfo> queryMovieInfoByUserId(Map<String, Object> map);

    MovieBasicInfo queryMovieInfoByMovieId(@Param("movieId") String movieId);


    int queryMovieInfoByUserIdCount(Map<String, Object> map);
}
