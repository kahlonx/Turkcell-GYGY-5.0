package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface MemberService {
    CreatedMemberResponse create(CreateMemberRequest request);
    List<ListMemberResponse> getAll();
    GetMemberResponse getById(Integer id);
    UpdatedMemberResponse update(Integer id, UpdateMemberRequest request);
    void delete(Integer id);
}
