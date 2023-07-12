package com.sh.hairball.member.model.vo;

public class Member {
	private int Id; // 시퀀스 고유넘버
    private String MemberId;
    private String Password;
    private String Name;
    private String Email;
    private String Phone;
    private String Address;
    private MemberRole memberRole;
    private Provider Provider; // 소셜로그인했을때 제공자가 누구인지 enum처리 할것.
    
    
	public Member() {
		super();
	}

	public Member(int id, String memberId, String password, String name, String email, String phone, String address,
			MemberRole memberRole, com.sh.hairball.member.model.vo.Provider provider) {
		super();
		Id = id;
		MemberId = memberId;
		Password = password;
		Name = name;
		Email = email;
		Phone = phone;
		Address = address;
		this.memberRole = memberRole;
		Provider = provider;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMemberId() {
		return MemberId;
	}

	public void setMemberId(String memberId) {
		MemberId = memberId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public Provider getProvider() {
		return Provider;
	}

	public void setProvider(Provider provider) {
		Provider = provider;
	}

	@Override
	public String toString() {
		return "Member [Id=" + Id + ", MemberId=" + MemberId + ", Password=" + Password + ", Name=" + Name + ", Email="
				+ Email + ", Phone=" + Phone + ", Address=" + Address + ", memberRole=" + memberRole + ", Provider="
				+ Provider + "]";
	}
    
	

}
