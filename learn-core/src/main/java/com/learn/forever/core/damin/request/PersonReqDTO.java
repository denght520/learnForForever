package com.learn.forever.core.damin.request;

import com.learn.forever.core.annotation.NotNull;
import com.learn.forever.core.listener.Listener;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: learnForForever
 * @className: PersonReqDTO
 * @author: denghaitao
 * @date: 2020/1/13
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
@Data
public class PersonReqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Listener listeners;

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String userName;

    private Integer age;

    /**1:男；3女*/
    private Integer sex;

    public void addListevent(Listener personReqDTO){
        this.listeners = personReqDTO;
    }

    public void deal(){
        listeners.dealListenerEvent(this);
    }
}
