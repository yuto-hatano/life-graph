package com.lifegraph.team20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifegraph.team20.models.Parent;
import com.lifegraph.team20.payload.request.LifeGraphData;
import com.lifegraph.team20.repository.ChildGraphRepository;
import com.lifegraph.team20.repository.ParentGraphRepository;

@Service
public class LifeGraphsService {

  @Autowired
  private ParentGraphRepository parentRepository;
  @Autowired
  private ChildGraphRepository childRepository;

  /**
   * 登録・編集のメイン処理
   * 
   * @param data 登録・編集リクエスト
   */
  public void resiter(LifeGraphData data) {
    // parent table
    long parentId = resiterParent(data.getUserId());
    // child table
    registerChild(parentId, data);
  }

  /**
   * 親グラフの登録・更新を行う
   * 
   * @param userId ユーザーID
   * @return 親グラフID
   */
  private long resiterParent(long userId) {
    // exists parent by userId
    long parentId;
    Optional<Parent> parent = parentRepository.findByUserId(userId);
//    if (parentRepository.existsByUserId(userId)) {
    if (parent.isPresent()) {
      // update
      parentId = parent.get().getId();
    } else {
      // insert
      parentId = parentRepository.insert(userId);
    }
    return parentId;
  }

  private void registerChild(Long parentId, LifeGraphData data) {
    // exists parent by userId
    if (childRepository.existsByUserIdAndAge(parentId, data.getAge())) {
      // update

    } else {
      // insert

    }
  }
}