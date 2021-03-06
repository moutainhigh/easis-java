package cn.nkpro.easis.co.fs.defaults;

import cn.nkpro.easis.co.fs.FSConfig;
import cn.nkpro.easis.co.fs.FileUploadStatus;
import cn.nkpro.easis.basic.NkProperties;
import cn.nkpro.easis.co.fs.FSSupport;
import cn.nkpro.easis.security.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class DefaultFileSupportImpl implements FSSupport<Map<String, Object>> {

    private Format format = new SimpleDateFormat(String.format("yyyyMMdd%sHHmmss", File.separator));

    @Autowired
    private NkProperties properties;

    @Autowired
    private UserAccountService accountService;

    @Override
    public ResponseEntity<Map<String, Object>> init(FSConfig file) {

        Map<String, Object> header = new LinkedHashMap<>();
        header.put("NK-Token",accountService.createToken().get("accessToken"));
        header.put("key", "${filename}");


        Map<String, Object> response = new LinkedHashMap<>();
        response.put("host","/api/fs/upload");
        response.put("path","");
        response.put("filename","");
        response.put("header",header);

        return ResponseEntity.ok(response);
    }

    @Override
    public FileUploadStatus upload(MultipartFile file){

        FileUploadStatus status = new FileUploadStatus();
        OutputStream os = null;
        InputStream is = null;
        try {
            String url = String.join(
                    File.separator,
                    format.format(new Date()),
                    UUID.randomUUID().toString(),
                    file.getOriginalFilename()
            ).replace("\\","/");
            String path = String.join(
                    File.separator,
                    properties.getFileRootPath(),
                    url
            ).replace("\\","/");
            File targetFile = new File(path);
            if(targetFile.getParentFile().mkdirs()){
                os=new FileOutputStream(targetFile);
                is=file.getInputStream();
                byte[] b=new byte[1024];
                //????????????????????????????????????
                while(is.read(b) !=(-1)){
                    os.write(b);
                }
                status.setName(file.getOriginalFilename());
                status.setStatus("done");
                status.setUrl(url);
                status.setThumbUrl(url);
            }else{
                status.setStatus("error");
                status.setMessage("??????????????????????????????");
            }
        } catch (IOException e) {
            status.setStatus("error");
            status.setMessage(e.getMessage());
        }finally {
            if(os!=null){
                try {
                    os.flush();
                    os.close();
                } catch (IOException ignored) {}
            }
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
        return status;
    }

    @Override
    public void moveTo(String source, String target) {

    }

    @Override
    public ResponseEntity download(String url) {
        return ResponseEntity.ok("/api/fs/d/"+url);
    }

    public ResponseEntity downloadFile(String url) {

        File file = new File(String.join(
                File.separator,
                properties.getFileRootPath(),
                url
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
                new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        headers.setContentLength(file.length());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }
}