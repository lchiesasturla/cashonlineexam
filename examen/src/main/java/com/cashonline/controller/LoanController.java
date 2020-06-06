package com.cashonline.controller;

import com.cashonline.models.entity.Loan;
import com.cashonline.pojo.Paging;
import com.cashonline.repository.LoanRepository;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("")
    public HashMap<String, Object> findLoansPageable(@RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "user_id", required = false) Long idUser) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        Page<Loan> pageRequest = null;
        boolean flagError = false;
        if(page > 0 && size > 0){
            Pageable pageable = PageRequest.of(page - 1, size);
            
            if (idUser == null) {
                pageRequest = loanRepository.findWithPage(pageable);
            } else {
                    pageRequest = loanRepository.findByIdUser(idUser, pageable);

            }
        }else{
            flagError = true;
        }
        
        if(!flagError){
            response.put("paging", new Paging(page, size, pageRequest.getTotalElements()));
            response.put("items", pageRequest.getContent());
        }else{
            response.put("error", "The page and the size of the page must be greater than 0.");
        }


        return response;
    }

}
