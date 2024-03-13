package HB_CAPE_MAK.hb_cape_makindu.controller;

import HB_CAPE_MAK.hb_cape_makindu.entity.BusinessModel;
import HB_CAPE_MAK.hb_cape_makindu.service.BusinessModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/business")
public class BusinessController {
    @Autowired
    private BusinessModelServiceImpl businessModelService;

    @GetMapping
    List<BusinessModel> getAllBusinessModel(){ return businessModelService.findAll();}

    @GetMapping(path = "/asc")
    List <BusinessModel> getAllBusinessModelSortedAsc(){return businessModelService.findAllSorted();}

    @GetMapping(path = "/{id}")
    public BusinessModel getBusinessModelById(@PathVariable Long id){ return businessModelService.findById(id);}

}
