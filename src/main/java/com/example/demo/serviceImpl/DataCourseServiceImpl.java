package com.example.demo.serviceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.DataCoursesDTO;
import com.example.demo.DTO.LessonsDTO;
import com.example.demo.model.DataCourses;
import com.example.demo.repository.DataCoursesRepository;
import com.example.demo.service.DataCourseService;
import com.example.demo.utils.FileUtils;
import org.springframework.util.StringUtils;

@Service
public class DataCourseServiceImpl implements DataCourseService{

	@Autowired
	private DataCoursesRepository dataCoursesRepository;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<DataCourses> getAllFile() {
		// TODO Auto-generated method stub
		return this.dataCoursesRepository.findAll();
	}

	@Override
	public DataCourses getFileByID(Long ID) {
		// TODO Auto-generated method stub
		return this.dataCoursesRepository.findById(ID).get();
	}

	@Override
	public DataCourses UploadFile(MultipartFile image, LessonsDTO lesson) {
		// TODO Auto-generated method stub
		fileUtils.validateFile(image);

        try {
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            Date date = new Date();
            DataCoursesDTO File = new DataCoursesDTO(fileName, image.getContentType(), image.getBytes(),date, null, null, null, true, lesson.getID());
            DataCourses data = this.modelMapper.map(File, DataCourses.class);
            return this.dataCoursesRepository.save(data);
        } catch (Exception e) {
            throw new RuntimeException("Upload image error");
        }
	}

}
