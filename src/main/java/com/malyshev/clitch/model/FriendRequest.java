package com.malyshev.clitch.model;

import javax.persistence.*;

@Entity
@Table(name = "friendRequest")
public class FriendRequest {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_user_id")
    private User idUser;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_friend_id")
    private User idFriend;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public void setIdFriend(User idFriend) {
        this.idFriend = idFriend;
    }

    public User getIdFriend() {
        return idFriend;
    }

    public User getIdUser() {
        return idUser;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idFriend=" + idFriend +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendRequest that = (FriendRequest) o;

        if (id != that.id) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        return idFriend != null ? idFriend.equals(that.idFriend) : that.idFriend == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (idFriend != null ? idFriend.hashCode() : 0);
        return result;
    }
}
