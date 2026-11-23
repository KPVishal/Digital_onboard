package com.bijlipay.controller;

import com.bijlipay.model.*;
//import com.bijlipay.model.DocumentCategoryMapping;
import com.bijlipay.service.DocumentService;
import com.bijlipay.service.MerchantCategoryCodeService;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/document")
public class DocumentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;
    @Autowired
    private MerchantCategoryCodeService merchantCategoryCodeService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    /****
     *  Document Category Crud Operations
     */

    /* Document Category Creation */
    @PostMapping("/createDocumentCategory")
    public ResponseEntity<?> createCategory(HttpServletRequest request, @RequestBody DocumentCategory documentCategory) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            DocumentCategory document = documentService.isCategoryExists(documentCategory);
            if (document != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "category already created"), HttpStatus.CONFLICT);
            }
            DocumentCategory category = documentService.createCategory(documentCategory);
            if (category != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", category), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "category not created"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Get list of Category details
     *  1 (Active) - active Category details
     */
    @GetMapping({"/documentCategoryList", "/documentCategoryList/{active}"})
    public ResponseEntity<?> getPlanData(HttpServletRequest request, @PathVariable("active") Optional<Boolean> active) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<DocumentCategory> categoryDetails = null;
            if (active.isPresent()) {
                categoryDetails = documentService.getActiveCategory(active.get());
            } else {
                categoryDetails = documentService.listOfCategory();
            }
            if (categoryDetails != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", categoryDetails), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "category list not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Update the Existing Category of Document */
    @PutMapping("/updateDocumentCategory/{id}")
    public ResponseEntity<?> updateCategory(HttpServletRequest request, @PathVariable("id") long id, @RequestBody DocumentCategory documentCategory) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            DocumentCategory category = documentService.getCategory(id);
            if (category == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Category not found"), HttpStatus.NOT_FOUND);
            }
            DocumentCategory document = documentService.updateCategory(id, documentCategory);
            if (document != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", document), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Category not updated"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Deactivate Category of Document */
    @DeleteMapping("/deleteDocumentCategory/{id}")
    public ResponseEntity<?> deleteCategory(HttpServletRequest request, @PathVariable("id") long id) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            DocumentCategory category = documentService.getCategory(id);
            if (category == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Category not found"), HttpStatus.NOT_FOUND);
            }
            documentService.deActiveCategory(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Category deactivated"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Fetch single Category of Document */
    @GetMapping("/getDocumentCategory/{id}")
    public ResponseEntity<?> getCategory(HttpServletRequest request, @PathVariable("id") long id) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.info("document check -- {} ", id);
            DocumentCategory category = documentService.getCategory(id);
            if (category != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", category), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "category list not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    /***
     * Document Type Crud Operations
     */

    /* Document Type Creation */
    @PostMapping("/createDocumentType")
    public ResponseEntity<?> createDocumentType(HttpServletRequest request, @RequestBody DocumentType documentType) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            DocumentType type = documentService.isTypeExists(documentType);
            if (type != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Document type exists"), HttpStatus.CONFLICT);
            }
            DocumentType document = documentService.CreateType(documentType);
            if (document != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", document), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Document type not created"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Get Document Type details
     *  1 (active) - active document types
     */
    @GetMapping({"/documentTypeDetails", "/documentTypeDetails/{active}"})
    public ResponseEntity<?> typeDetails(HttpServletRequest request, @PathVariable("active") Optional<Boolean> active) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<DocumentType> documentTypes = null;
            if (active.isPresent()) {
                documentTypes = documentService.getActiveTypes(active.get());
            } else {
                documentTypes = documentService.getAllTypes();
            }
            if (documentTypes != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", documentTypes), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Document type not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Update Document type */
    @PutMapping("/updateDocumentType/{id}")
    public ResponseEntity<?> updateDocumentType(HttpServletRequest request, @PathVariable("id") long id, @RequestBody DocumentType documentType) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            DocumentType type = documentService.checkDocumentType(id);
            if (type != null) {
                DocumentType document = documentService.updateType(id, documentType);
                if (document != null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", document), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Document type not updated"), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Document type not found"), HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Deactivate Document Type */
    @DeleteMapping("/deleteDocumentType/{id}")
    public ResponseEntity<?> deleteDocumentType(HttpServletRequest request, @PathVariable("id") long id) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.info("test data -- : {}", id);
            DocumentType type = documentService.checkDocumentType(id);
            if (type != null) {
                logger.info("out -- : {}", type.getName());
                documentService.deleteType(id);
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Document Type deactivated"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Document type not found"), HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Fetch Single Document Type */
    @GetMapping("/getDocumentType/{id}")
    public ResponseEntity<?> documentType(HttpServletRequest request, @PathVariable("id") long id) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("document check -- {} ", id);
            DocumentType type = documentService.checkDocumentType(id);
            if (type != null) {
                logger.info("success -- : {}", type.getName());
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", type), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "failed"), HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

//    /*****
//     * Document type and Category mapping
//     *  with Merchant Category code (mcc)
//     *  mccCategoryIndicator details
//     *  0 - No mcc
//     *  1 - mapped mcc
//     *  2 - Other mcc
//     */

    /* Document type and category map with Mcc */
//    @PostMapping("/createDocumentCategoryTypeMapping")
//    public ResponseEntity<?> createDocumentCategory(@RequestBody DocumentCategoryMapping documentCategoryMapping) throws Exception {
//        DocumentCategoryMapping categoryMapping = documentService.isMappingExists(documentCategoryMapping);
//        if(categoryMapping == null){
//            DocumentCategoryMapping document = documentService.createDocumentCategory(documentCategoryMapping);
//            if(document != null){
//                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", document), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "mapping not created"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Mapping created already"), HttpStatus.CONFLICT);
//    }

    /* List of Document Category mapping */
//    @GetMapping({"/documentMappingTypeData", "/documentMappingTypeData/{id}"})
//    public ResponseEntity<?> documentMappingData(@PathVariable("id") Optional<Long> id ) throws Exception {
//        List<DocumentCategoryMapping> categoryMappings = null;
//        if(id.isPresent()){
//            categoryMappings = documentService.getSingleMapping(id);
//        } else {
//            categoryMappings = documentService.getAllMapping();
//        }
//        if(categoryMappings != null){
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", categoryMappings), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Mapping list not found"), HttpStatus.NOT_FOUND);
//    }

    /* Update Document Category Mapping */
//    @PutMapping("/updateDocumentCategoryTypeMapping/{id}")
//    public ResponseEntity<?> updateCategoryMapping(@PathVariable("id") long id, @RequestBody DocumentCategoryMapping documentCategoryMapping) throws Exception {
//        DocumentCategoryMapping categoryMapping = documentService.getCategoryMap(id);
//        if(categoryMapping != null){
//            DocumentCategoryMapping document = documentService.updateCategoryMapping(id,documentCategoryMapping);
//            if(document != null){
//                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", document), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "mapping not updated"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "mapping not found"), HttpStatus.CONFLICT);
//    }

    /* Delete Document Category mapping */
//    @DeleteMapping("/deleteDocumentCategoryTypeMapping/{id}")
//    public ResponseEntity<?> deleteCategoryMapping(@PathVariable("id") long id) throws Exception {
//        DocumentCategoryMapping categoryMapping = documentService.getCategoryMap(id);
//        if(categoryMapping != null){
//            documentService.deleteCategoryMapping(categoryMapping);
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success"), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "mapping not found"), HttpStatus.CONFLICT);
//    }

    /* Biz Category Document list based on MCC */
    @GetMapping("/bizCategoryDocument/{mcc}")
    public ResponseEntity<?> listOfDocument(HttpServletRequest request, @PathVariable("mcc") String mcc) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            boolean categoryCode = merchantCategoryCodeService.existsMcc(mcc);
            logger.info("check -- :{}", categoryCode);
            if (categoryCode) {
                List<SubDocumentType> mappingList = documentService.getBizDocuments(mcc);
                if (mappingList != null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mappingList), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Documents not found"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "mcc not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Basic Documents list*/
    @GetMapping("/basicDocuments")
    public ResponseEntity<?> basicDocuments(HttpServletRequest request) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            int value = 0;// to collect basic document details
            List<SubDocumentType> mappingList = documentService.getBasicDocuments(value);
            if (mappingList != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mappingList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "basic documents not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /***
     *  Sub document mapping with Document Type
     *  crud operations
     *  0 - No mcc
     *  1 - mapped mcc
     *  2 - Other mcc
     */

    /* Create - Sub Document */
    @PostMapping("/createSubDocument")
    public ResponseEntity<?> createSubDocument(HttpServletRequest request, @RequestBody SubDocumentType subDocumentType) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            SubDocumentType subDocument = documentService.isMappingExists(subDocumentType);
            if (subDocument == null) {
                SubDocumentType documentType = documentService.createSubDocument(subDocumentType);
                if (documentType != null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", documentType), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Document type not created"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Mapping already exists"), HttpStatus.CONFLICT);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Get Sub Document Type details
     *  1 (active) - active document types
     */
    @GetMapping({"/subDocumentTypeDetails", "/subDocumentTypeDetails/{active}"})
    public ResponseEntity<?> subDocumentTypeDetails(HttpServletRequest request, @PathVariable("active") Optional<Boolean> active) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<SubDocumentType> documentTypes = null;
            if (active.isPresent()) {
                documentTypes = documentService.getDocumentActiveTypes(active.get());
            } else {
                documentTypes = documentService.getDocumentAllTypes();
            }
            if (documentTypes != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", documentTypes), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "sub Document type not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Update Document type */
    @PutMapping("/updateSubDocumentType/{id}")
    public ResponseEntity<?> updateSubDocumentType(HttpServletRequest request, @PathVariable("id") long id, @RequestBody SubDocumentType documentType) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            SubDocumentType type = documentService.checkSubDocumentType(id);
            if (type != null) {
                SubDocumentType document = documentService.updateSubDocumentType(id, documentType);
                if (document != null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", document), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "sub Document type not updated"), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "sub Document type not found"), HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }

    }

    /* Deactivate Document Type */
    @DeleteMapping("/deleteSubDocumentType/{id}")
    public ResponseEntity<?> deleteSubDocumentType(HttpServletRequest request, @PathVariable("id") long id) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.info("data -- : {}", id);
            SubDocumentType type = documentService.checkSubDocumentType(id);
            if (type != null) {
                logger.info("out -- : {}", type.getName());
                documentService.deleteSubDocumentType(id);
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "sub Document Type deactivated"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "sub Document type not found"), HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Fetch Single Sub Document Type */
    @GetMapping("/getSubDocumentType/{id}")
    public ResponseEntity<?> subDocumentType(HttpServletRequest request, @PathVariable("id") long id) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.info("document check -- {} ", id);
            SubDocumentType type = documentService.checkSubDocumentType(id);
            if (type != null) {
                logger.info("success -- : {}", type.getName());
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", type), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "failed"), HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Implementation Document Details for SO App
     */

    @GetMapping("/implementationDocuments")
    public ResponseEntity<?> implementationDocuments(HttpServletRequest request) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("Implementation documents -- : ");
            List<SubDocumentType> documentTypeList = documentService.collectImplementationDocuments();
            if (documentTypeList != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", documentTypeList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "document not found"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getBankProofDocuments")
    public ResponseEntity<?>getBankAccountDocuments(HttpServletRequest request){
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
                List<SubDocumentType> mappingList = documentService.getBankAccountDocuments();
                if (mappingList != null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mappingList), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Documents not found"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getOSVDocumentList")
    public ResponseEntity<?>getOSVDocumentList(HttpServletRequest request){
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<SubDocumentType> mappingList = documentService.getOSVDocumentList();
            if (mappingList != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mappingList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Documents not found"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
//    @GetMapping("/getNegativeCheckDocumentList")
//    public ResponseEntity<?>negativeCheckDocumentList(HttpServletRequest request){
//        String authtoken = request.getHeader("Authorization");
//        logger.info("authtoken==={}", authtoken);
//        User curuser = getSessionUser();
//        logger.info("Session User {}", curuser);
//        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
//        if (authtoken.equals(userauth.getToken())) {
//            List<SubDocumentType> mappingList = documentService.getNegativeCheckDocumentList();
//            if (mappingList != null) {
//
//                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mappingList), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Documents not found"), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
//        }
//    }

}
