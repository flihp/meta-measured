SUMMARY = "a bootable image with tpm stuff using wic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
    "

require recipes-tpm/images/core-image-tpm.inc

SRC_URI = "file://${FILE_DIRNAME}/${BPN}.wks.in"

IMAGE_FSTYPES = "wic"
IMAGE_TYPEDEP_wic = "ext4"

WKS_FILE = "${BPN}.wks.in"
WKS_FILE_DEPENDS = "dosfstools-native mtools-native gptfdisk-native"

RDEPENDS_wic-image-tpm = "grub-efi"

IMAGE_ROOTFS_EXTRA_SPACE = "2000"
