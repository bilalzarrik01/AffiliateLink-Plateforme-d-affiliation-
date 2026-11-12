package com.affiliatelink.profile.dto ;


import com.affiliatelink.profile.entity.ProfileStatus;

import jakarta.validation.constraints.NotNull;

public record UpdateProfileStatusRequest(@NotNull ProfileStatus status){

}