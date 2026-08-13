package eu.darkbot.api.managers;

import eu.darkbot.api.API;
import eu.darkbot.api.events.Event;
import eu.darkbot.api.game.entities.Ship;
import eu.darkbot.api.game.group.GroupMember;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Provides access to inspect and manage the in-game group (formerly known as outfit).
 */
public interface GroupAPI extends API.Singleton {
    /**
     * @return true if {@link HeroAPI} is in group.
     */
    boolean hasGroup();

    /**
     * Numerical id for the group you are currently in
     * @return id of the group if you are in one, 0 otherwise
     */
    int getId();

    /**
     * Size of the current group, including yourself.
     * In a group of you and one other player, the size is 2.
     *
     * @return size of the group if in one, 0 otherwise
     */
    int getSize();

    /**
     * @return The maximum size of groups, currently in-game 8
     */
    int getMaxSize();

    /**
     * @return if group is open and anyone can invite other players
     */
    boolean isOpen();

    /**
     * @return if you are the leader of your group
     */
    boolean isLeader();

    /**
     * If all conditions to be able to invite players are met, for example:
     *  - You're not in a group
     *  - You are the leader of the group or invites are open
     *  - The group isn't full
     *
     * @return if able to invite players at this time, false otherwise
     */
    boolean canInvite();

    /**
     * The given list will stay updated whenever members are added or
     * removed, however, you cannot modify this list yourself.
     * Works similar to collections provided in {@link EntitiesAPI}
     *
     * @return list of all other players in the group, excluding yourself.
     */
    List<? extends GroupMember> getMembers();

    /**
     * If you lock a player in-game on the space map, in the outfit
     * window they become selected (appear highlighted)
     *
     * @return The currently selected member in outfit window, null if none is selected
     */
    @Nullable GroupMember getSelectedMember();

    /**
     * Find a group member by their user id
     * @param id the user id to search for
     * @return the group member if found, null otherwise.
     */
    @Nullable GroupMember getMember(int id);

    /**
     * Find a group member by their ship entity
     * @param ship The player ship entity to search for
     * @return the group member if found, null otherwise.
     */
    @Nullable default GroupMember getMember(Ship ship) {
        return getMember(ship.getId());
    }

    /**
     * @return The list of member invites that are currently pending
     */
    List<? extends GroupMember.Invite> getInvites();

    /** Selects a member in the group window, when the transport supports it. */
    default boolean selectMember(int id) { return false; }

    /** Sends an invitation by username, when the transport supports it. */
    default boolean invite(String username) { return false; }

    /** Sends an invitation by user id, when the transport supports it. */
    default boolean invite(int id) { return false; }

    /** Accepts an incoming invitation, when the transport supports it. */
    default boolean acceptInvite(GroupMember.Invite invite) { return false; }

    /** Rejects an incoming invitation, when the transport supports it. */
    default boolean rejectInvite(GroupMember.Invite invite) { return false; }

    /** Cancels an outgoing invitation. */
    default boolean cancelInvite(int id) { return false; }

    /** Leaves the current group. */
    default boolean leaveGroup() { return false; }

    /** Changes the invitation mode: false=boss-only, true=free-for-all. */
    default boolean setOpen(boolean open) { return false; }

    /** Toggles invitation blocking. */
    default boolean toggleInvitationBlock() { return false; }

    /** Event fired when a member appears in the current group snapshot. */
    class GroupMemberAddedEvent implements Event {
        private final GroupMember member;

        public GroupMemberAddedEvent(GroupMember member) {
            this.member = member;
        }

        public GroupMember getMember() {
            return member;
        }
    }

    /** Event fired when an existing member receives a packet update. */
    class GroupMemberUpdatedEvent implements Event {
        private final GroupMember member;

        public GroupMemberUpdatedEvent(GroupMember member) {
            this.member = member;
        }

        public GroupMember getMember() {
            return member;
        }
    }

    /** Event fired when a member disappears from the current group snapshot. */
    class GroupMemberRemovedEvent implements Event {
        private final GroupMember member;

        public GroupMemberRemovedEvent(GroupMember member) {
            this.member = member;
        }

        public GroupMember getMember() {
            return member;
        }
    }

    /** Event fired when an invitation is added or removed by the server. */
    class GroupInvitationEvent implements Event {
        private final GroupMember.Invite invite;
        private final boolean removed;

        public GroupInvitationEvent(GroupMember.Invite invite, boolean removed) {
            this.invite = invite;
            this.removed = removed;
        }

        public GroupMember.Invite getInvite() {
            return invite;
        }

        public boolean isRemoved() {
            return removed;
        }
    }

    /** Event fired when the server closes the current group. */
    class GroupClosedEvent implements Event {
        private final int groupId;

        public GroupClosedEvent(int groupId) {
            this.groupId = groupId;
        }

        public int getGroupId() {
            return groupId;
        }
    }

}
