package com.malyshev.clitch.model;


import javax.persistence.*;

@Entity
@Table(name = "friendConfirmed")
public class FriendConfirmed {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "friend_id")
    private User friend;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getFriend() {
        return friend;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", user=" + user +
                ", friend=" + friend +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendConfirmed that = (FriendConfirmed) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return friend != null ? friend.equals(that.friend) : that.friend == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (friend != null ? friend.hashCode() : 0);
        return result;
    }
}

