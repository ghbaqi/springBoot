package com.example.demo.uploadfile;

import com.example.demo.fastdfs.FastDFSClient;
import com.example.demo.fastdfs.FastDFSFile;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(UploadController.class);

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://temp//";

    /**
     * 文件上传
     */
    @PostMapping(value = "/file/upload")
    public String upload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message","please select a file");
            return "redirect:/file/uploadresult";
        }

        /*try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message","成功上传");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            String path=saveFile(file);
            redirectAttributes.addFlashAttribute("message","上传成功 path = "+path);
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message","上传失败");
            e.printStackTrace();
        }


        return "redirect:/file/uploadresult";
    }


    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        String fileName=multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream=multipartFile.getInputStream();
        if(inputStream!=null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath==null) {
            logger.error("upload file failed,please upload again!");
        }
        String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+ "/"+fileAbsolutePath[1];
        return path;
    }
}
