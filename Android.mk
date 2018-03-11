LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MANIFEST_FILE := commons/src/main/AndroidManifest.xml
LOCAL_SRC_FILES := $(call all-java-files-under,commons)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/commons/src/main/res
LOCAL_RESOURCE_DIR += frameworks/support/v7/recyclerview/res
LOCAL_RESOURCE_DIR += frameworks/support/v7/cardview/res
LOCAL_RESOURCE_DIR += frameworks/support/v7/appcompat/res
LOCAL_RESOURCE_DIR += frameworks/support/design/res

LOCAL_STATIC_JAVA_LIBRARIES += jodatime
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v4
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-recyclerview
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-cardview
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v13
LOCAL_STATIC_JAVA_LIBRARIES += android-support-design

LOCAL_STATIC_JAVA_AAR_LIBRARIES := roundedimageview

LOCAL_MODULE := KatsunaCommon

LOCAL_SDK_VERSION := $(SUPPORT_CURRENT_SDK_VERSION)

#LOCAL_USE_AAPT2 := true
LOCAL_AAPT_INCLUDE_ALL_RESOURCES := true
LOCAL_AAPT_FLAGS := --auto-add-overlay
LOCAL_AAPT_FLAGS += --generate-dependencies
LOCAL_AAPT_FLAGS += --no-version-vectors
LOCAL_JAR_EXCLUDE_FILES := none
#LOCAL_JAVA_LANGUAGE_VERSION := 1.7

include $(BUILD_STATIC_JAVA_LIBRARY)
