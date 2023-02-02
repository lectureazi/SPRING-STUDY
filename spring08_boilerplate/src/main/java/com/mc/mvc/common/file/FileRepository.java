package com.mc.mvc.common.file;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository {
	
	@Insert("insert into file_info(origin_file_name, rename_file_name, save_path, group_name, gn_idx)"
			+ " values(#{originFileName},#{renameFileName},#{savePath},#{groupName}, #{gnIdx})")
	void insertFileInfo(FileInfo fileInfo);
	
	@Select("select * from file_info where is_del = 0 and fl_idx=#{flIdx}")
	FileInfo selectFileInfo(String flIdx);

	@Select("select * from file_info where is_del = 0 and group_name = #{groupName} and gn_idx = #{gnIdx}")
	List<FileInfo> selectFileWithGroup(Map<String, Object> group);

	@Update("update file_info set is_del = 1 where group_name = #{groupName} and gn_idx = #{gnIdx} ")
	void deleFileByGroup(Map<String, Object> of);


}
