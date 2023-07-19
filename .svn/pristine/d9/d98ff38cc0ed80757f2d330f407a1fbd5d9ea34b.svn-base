package org.nic.pmusha.usereo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_action_log")
@NoArgsConstructor
@AllArgsConstructor
public class UserActionLogEO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5292318515092014785L;

    @Id
    @GenericGenerator(name = "useractionlog", strategy = "increment")
    @GeneratedValue(generator = "useractionlog")
    @Column(name = "id")
    private Integer Id;

    @Column(name = "action_id")
    private Integer actionId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "action_time")
    private LocalDateTime actionTime;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "remarks")
    private String remarks;


    public UserActionLogEO(Integer actionId, String userId, LocalDateTime actionTime, String ipAddress, String remarks) {
        this.actionId = actionId;
        this.userId = userId;
        this.actionTime = actionTime;
        this.ipAddress = ipAddress;
        this.remarks = remarks;
    }
}