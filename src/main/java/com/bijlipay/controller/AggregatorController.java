package com.bijlipay.controller;

import com.bijlipay.model.AggregatorData;
import com.bijlipay.service.AggregatorService;
import com.bijlipay.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/aggregator")
public class AggregatorController {

    @Autowired
    private AggregatorService aggregatorService;

    @PostMapping("/createAggregator")
    public ResponseEntity<?> createAggregator(@RequestBody AggregatorData aggregator){
        boolean isAggExists = aggregatorService.isAggregatorExists(aggregator);
        if (!isAggExists){
            AggregatorData savedAgg = aggregatorService.saveAggregator(aggregator);
            if (savedAgg != null){
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"success",savedAgg),HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT,"Aggregator not created. Please try Again!!"),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,"Aggregator already exists"),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAggregatorList")
    public ResponseEntity<?> getAggregatorList(){
        List<AggregatorData> aggregatorList = aggregatorService.fetchAllAggList();
        if (!aggregatorList.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"success",aggregatorList),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND,"Data not found"),HttpStatus.NOT_FOUND);
    }

}
