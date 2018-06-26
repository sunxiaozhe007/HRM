package controller;

import domain.Document;
import domain.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.HrmService;
import util.HrmConstants;
import util.PageModel;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class DocumentController {
    /**
     * �Զ�ע��Service
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * ����login����
     */
    @RequestMapping(value = "/document/selectDocument")
    public String selectDocument(Model model, Integer pageIndex, @ModelAttribute Document document){
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        //��ѯ�û���Ϣ
        List<Document> documents = hrmService.findDocument(document,pageModel);
        model.addAttribute("documents",documents);
        model.addAttribute("pageModel",pageModel);
        return "document/document";
    }

    /**
     * �����������
     */
    @RequestMapping(value = "/document/addDocument")
    public ModelAndView addDocument(String flag, @ModelAttribute Document document, ModelAndView mv, HttpSession session) throws IOException {
        if (flag.equals("1")){
            mv.setViewName("document/showAddDocument");
        }else {

            //�ϴ��ļ�·��
            String path = session.getServletContext().getRealPath("/upload");
            //�ϴ��ļ���
            String fileName = document.getFile().getOriginalFilename();
            System.out.println(path);
            System.out.println(fileName);
            //���ϴ��ļ����浽һ��Ŀ���ļ���
            document.getFile().transferTo(new File(path+File.separator+fileName));

            //�������ݿ�
            //����fileName
            document.setFileName(fileName);

            //���ù�����User����
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            document.setUser(user);

            //�������ݿ�
            System.out.println(document);
            hrmService.addDocument(document);
            //����

            mv.setViewName("document/selectDocument");
        }
        return mv;
    }

    /**
     * ����ɾ���ļ�����
     */
    @RequestMapping(value = "/document/removeDocument")
    public ModelAndView removeDocument(String ids,ModelAndView mv){
        //�ֽ��ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ���ĵ�
            hrmService.removeDocumentById(Integer.valueOf(id));
        }
        //���ÿͻ�����ת����ѯ����
        mv.setViewName("redirect:/document/selectDocument");
        //����
        return mv;
    }

    /**
     * �����޸��ĵ�����
     */
    @RequestMapping(value = "/document/updateDocument")
    public ModelAndView updateDocument(String flag,@ModelAttribute Document document,ModelAndView mv){
        if (flag.equals("1")){
            //����id��ѯ�ĵ�
            Document target = hrmService.findDocumentById(document.getId());
            //����Model����
            mv.addObject("document",target);
            //������ת���޸�ҳ��
            mv.setViewName("document/showUpdateDocument");
        }else {
            //ִ���޸Ĳ���
            hrmService.modifyDocument(document);
            //���ÿͻ�����ת����ѯ����
            mv.setViewName("redirect:/document/selectDocument");
        }
        //����
        return mv;
    }

    /**
     * �����ĵ���������
     */
    @RequestMapping(value = "/document/downLoad")
    public ResponseEntity<byte[]> downLoad(Integer id,HttpSession session) throws IOException {
        //����id��ѯ�ĵ�
        Document target = hrmService.findDocumentById(id);
        String fileName = target.getFileName();
        System.out.println(fileName);
        //�ϴ��ļ�·��
        String path = session.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        //���Ҫ���ص�File����
        File file = new File(path + File.separator+fileName);

        //����springframework��HttpHeaders����
        HttpHeaders headers = new HttpHeaders();

        //������ʾ���ļ��������������������
        String downliadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");

        //֪ͨ�������attachment�����ط�ʽ����ͼƬ
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //201 HttpStatus.CREATED
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
    }

}
