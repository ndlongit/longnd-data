package com.posiba.button.dto;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DonorProfileDto extends BaseDto {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    private boolean artCultures;
    // private DateTime birthdate;
    private String city;
    // private String country;
    private boolean education;
    private boolean environment;
    private String firstName;
    private String lastName;
    private String gender;
    private boolean health;
    private boolean humanServices;
    private boolean hunger;
    private String location;
    private String phone;
    private String postalCode;
    private String privacy;
    private String profilePictureName;
    private String profilePictureUrl;

    private boolean socialIssues;
    private String state;
    private String status;
    private String email;
    private boolean savedProfile;

    private boolean poverty;
    private boolean religion;
    private boolean foreignAffairs;
    private DateTime birthdate;
    private Integer birthYear;
    private String birthDay;

    // public String getAddress() {
    // String address = "";
    // if (!isNullOrEmpty(this.city)) {
    // address = this.city + ", ";
    // }
    // if (!isNullOrEmpty(this.state)) {
    // address += this.state;
    // }
    //
    // if (isNullOrEmpty(this.city)) {
    // return "";
    // } else {
    // return address;
    // }
    // }
//    @JsonIgnore
    public DateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DateTime birthdate) {
        this.birthdate = birthdate;
    }
    @JsonIgnore
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthDay() {
        return birthDay;
    }
    @JsonIgnore
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isPoverty() {
        return poverty;
    }

    public void setPoverty(boolean poverty) {
        this.poverty = poverty;
    }

    public boolean isReligion() {
        return religion;
    }

    public void setReligion(boolean religion) {
        this.religion = religion;
    }

    public boolean isForeignAffairs() {
        return foreignAffairs;
    }

    public void setForeignAffairs(boolean foreignAffairs) {
        this.foreignAffairs = foreignAffairs;
    }

    public boolean isSavedProfile() {
        return savedProfile;
    }

    public void setSavedProfile(boolean savedProfile) {
        this.savedProfile = savedProfile;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getPrivacy() {
        if (privacy == null) {
            return "public";
        }
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    // There is no setter method for this field
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // public void setFullName(String fullName) {
    // this.fullName = fullName;
    // }

    // @NotBlank(message = "{email.not.entered}")
    public String getEmail() {
        // return email;
        return null;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // private Integer birthYear;
    // private String birthDay;
    private long birthdayAsLong;

    public long getBirthdayAsLong() {
        return birthdayAsLong;
    }

    public void setBirthdayAsLong(long birthdayAsLong) {
        if (birthdayAsLong > 0) {
            birthdate = new DateTime(birthdayAsLong);
        }
        
        this.birthdayAsLong = birthdayAsLong;
    }

    public boolean getArtCultures() {
        return artCultures;
    }

    public void setArtCultures(boolean artCultures) {
        this.artCultures = artCultures;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // public String getCountry() {
    // return country;
    // }
    //
    // public void setCountry(String country) {
    // this.country = country;
    // }

    public boolean getEducation() {
        return education;
    }

    public void setEducation(boolean education) {
        this.education = education;
    }

    public boolean getEnvironment() {
        return environment;
    }

    public void setEnvironment(boolean environment) {
        this.environment = environment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getHealth() {
        return health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public boolean getHumanServices() {
        return humanServices;
    }

    public void setHumanServices(boolean humanServices) {
        this.humanServices = humanServices;
    }

    public boolean getHunger() {
        return hunger;
    }

    public void setHunger(boolean hunger) {
        this.hunger = hunger;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    // public String getProfileName() {
    // return profileName;
    // }
    //
    // public void setProfileName(String profileName) {
    // this.profileName = profileName;
    // }

    public String getProfilePictureName() {
        return profilePictureName;
    }

    public void setProfilePictureName(String profilePictureName) {
        this.profilePictureName = profilePictureName;
    }

    public boolean getSocialIssues() {
        return socialIssues;
    }

    public void setSocialIssues(boolean socialIssues) {
        this.socialIssues = socialIssues;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static void handlleNullProperties(DonorProfileDto dto) {
        if (dto == null) {
            return;
        }

        // TODO Set empty values for NULL properties

        if (dto.getStatus() == null) {
            dto.setStatus("");
        }
        if (dto.getProfilePictureName() == null) {
            dto.setProfilePictureName("");
        }

        if (dto.getProfilePictureUrl() == null) {
            dto.setProfilePictureUrl("");
        }
    }
}
