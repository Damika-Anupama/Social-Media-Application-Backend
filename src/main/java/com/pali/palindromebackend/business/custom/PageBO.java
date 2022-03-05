package com.pali.palindromebackend.business.custom;

import com.pali.palindromebackend.business.SuperBO;
import com.pali.palindromebackend.dto.PageDTO;

import java.util.List;

/**
 * @author : Damika Anuapama Nanayakkara <damikaanupama@gmail.com>
 * @since : 01/06/2021
 **/
public interface PageBO extends SuperBO {
    public void savePage(PageDTO dto) throws Exception;

    public void updatePage(PageDTO dto) throws Exception;

    public void deletePage(int pageId) throws Exception;

    public List<PageDTO> getAllPages() throws Exception;

    public PageDTO getPage(int PageId) throws Exception;
}
