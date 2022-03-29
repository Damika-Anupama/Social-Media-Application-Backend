package com.pali.palindromebackend.business.custom.impl;

import com.pali.palindromebackend.business.custom.PageBO;
import com.pali.palindromebackend.business.util.EntityDTOMapper;
import com.pali.palindromebackend.dao.PageDAO;
import com.pali.palindromebackend.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
@Transactional
@Service
public class PageBOimpl implements PageBO {

    @Autowired
    private PageDAO dao;

    @Autowired
    private EntityDTOMapper mapper;

    public PageBOimpl() {
    }


    @Override
    public void savePage(PageDTO dto) throws Exception {
        dao.save(mapper.getPage(dto));
    }

    @Override
    public void updatePage(PageDTO dto) throws Exception {
        dao.save(mapper.getPage(dto));
    }

    @Override
    public void deletePage(int pageId) throws Exception {
        dao.deleteById(pageId);
    }

    @Override
    public List<PageDTO> getAllPages() throws Exception {
        return dao.findAll().stream().
                map(pages -> mapper.getPageDTO(pages)).collect(Collectors.toList());
    }

    @Override
    public PageDTO getPage(int pageId) throws Exception {
        return dao.findById(pageId).map(page -> mapper.getPageDTO(page)).get();
    }
}
