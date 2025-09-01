package lk.ijse.cmjd_111.CourseRegistration2025.controller;


import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseMaterialDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.CourseMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/material")
public class CourseMaterialController {
    private final CourseMaterialService courseMaterialService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CourseMaterialDTO> saveMaterial(
            @RequestParam String materialId,
            @RequestParam String fileName,
            @RequestParam String materialType,
            @RequestParam MultipartFile material,
            @RequestParam (required = false) String uploadAt,
            @RequestParam String courseId
    ){
        //step 1 - Create a DTO based on the received data
        var materialDTO = new CourseMaterialDTO();

        try {
            byte [] materialBytes = material.getBytes();
            String materialString = Base64.getEncoder().encodeToString(materialBytes);
            String uploadTime = uploadAt != null ? uploadAt : LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
            materialDTO.setMaterialId(materialId);
            materialDTO.setFileName(fileName);
            materialDTO.setMaterialType(materialType);
            materialDTO.setMaterial(materialString);
            materialDTO.setUploadAt(uploadTime);
            materialDTO.setCourseId(courseId);

        }catch (Exception ex){
            ex.printStackTrace();
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(materialDTO, HttpStatus.OK);
    }
    @GetMapping(value = "{materialId}")
    public ResponseEntity<CourseMaterialDTO> getSelectedMaterial(@PathVariable String materialId){
        if( materialId== null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(courseMaterialService.getSelectedCourseMaterial(materialId),
                    HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<List<CourseMaterialDTO>> getAllMaterials() {
        List<CourseMaterialDTO> allMaterials = courseMaterialService.getAllCourseMaterials();
        return new ResponseEntity<>(allMaterials, HttpStatus.OK);
    }
    @PatchMapping
    public void updateMaterial(@RequestParam String materialId,@RequestBody CourseMaterialDTO materialUpdateData){
        System.out.print("materialId: "+materialId);
        System.out.print(materialUpdateData);
    }
    @DeleteMapping
    public void deleteMaterial(@RequestHeader ("y-courseMaterialId")String materialId){
        System.out.print("id is to be deleted "+materialId);
    }
}
