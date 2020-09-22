#
# Copyright (C) 2020 Manos Saratsis
#
# This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, version 3.
#
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
#
LOCAL_PATH := $(call my-dir)
# Here is the final static library that apps can link against.
# Applications that use this library must specify
#
#   LOCAL_STATIC_ANDROID_LIBRARIES := \
#       KatsunaCommon \
#       android-support-v4 \
#       android-support-v7-appcompat \
#       android-support-v7-recyclerview \
#       android-support-v7-cardview \
#       android-support-v13 \
#       android-support-design
#
# in their makefiles to include the resources and their dependencies in their package.
include $(CLEAR_VARS)
LOCAL_USE_AAPT2 := true
# Not to be used in Oreo - Pie upstream flag
#LOCAL_AAPT_NAMESPACES := true
LOCAL_MODULE := KatsunaCommon
LOCAL_SDK_VERSION := $(SUPPORT_CURRENT_SDK_VERSION)

LOCAL_SRC_FILES := $(call all-java-files-under,commons)

LOCAL_RESOURCE_DIR := \
    $(LOCAL_PATH)/commons/src/main/res \
    prebuilts/sdk/current/extras/constraint-layout/res

LOCAL_STATIC_ANDROID_LIBRARIES := \
    android-support-v4 \
    android-support-v7-appcompat \
    android-support-v7-recyclerview \
    android-support-v7-cardview \
    android-support-v13 \
    android-support-design

LOCAL_STATIC_JAVA_LIBRARIES := \
    jodatime \
    android-support-constraint-layout \
    android-support-constraint-layout-solver \
    commonslang3

LOCAL_STATIC_JAVA_AAR_LIBRARIES += \
    fabtransformation

LOCAL_MANIFEST_FILE := commons/src/aosp/AndroidManifest.xml

#LOCAL_AAPT_INCLUDE_ALL_RESOURCES := true
LOCAL_AAPT_FLAGS += \
    --no-version-vectors

LOCAL_JAR_EXCLUDE_FILES := none

include $(BUILD_STATIC_JAVA_LIBRARY)
