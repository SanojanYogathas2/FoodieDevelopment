package com.project.foodie.dto.notification;

import com.project.foodie.dto.ModelMapper;
import com.project.foodie.model.notification.Notification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDTO implements ModelMapper<Notification>{
    private Long id;
    private String message;
    private Long userId;

    public NotificationDTO(Notification notification){
        this.mapToSelf(notification);
    }
    
    @Override
    public Notification getModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getModel'");
    }

    @Override
    public Notification mapToModel(Notification dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mapToModel'");
    }

    @Override
    public void mapToSelf(Notification notification) {
        this.setId(notification.getId());
        this.setMessage(notification.getMessage());
        this.setUserId(notification.getUser().getId());

    }
    
}
