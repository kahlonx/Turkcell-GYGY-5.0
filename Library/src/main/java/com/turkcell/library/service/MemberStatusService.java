package com.turkcell.library.service;

import com.turkcell.library.dto.*;
import java.util.List;

public interface MemberStatusService {
    CreatedMemberStatusResponse create(CreateMemberStatusRequest request);
    List<ListMemberStatusResponse> getAll();
    GetMemberStatusResponse getById(Integer id);
    UpdatedMemberStatusResponse update(Integer id, UpdateMemberStatusRequest request);
    void delete(Integer id);
}
