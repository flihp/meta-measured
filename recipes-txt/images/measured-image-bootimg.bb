# bootable image with tboot
LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 \
    "

SRC_URI = " \
    file://syslinux.cfg;md5=7ae4fc16f1e54b4dc232e76ffd47b0d5 \
    file://grub.cfg;md5=9b0245d085f18f5b93dea9ddc18c8174 \
"

IMAGE_INSTALL += "base-passwd packagegroup-tboot"

INITRD_IMAGE ?= "core-image-tpm-initramfs"
INITRD = "${DEPLOY_DIR_IMAGE}/${INITRD_IMAGE}-${MACHINE}.cpio.gz"

ROOTFS_IMAGE ?= "core-image-tpm"
ROOTFS = "${DEPLOY_DIR_IMAGE}/${ROOTFS_IMAGE}-${MACHINE}.ext3"

NOHDD = "1"

TBOOT_CMDLINE = "loglvl=all logging=serial,vga,memory"
KERNEL_CMDLINE = "ramdisk_size=32768 root=/dev/ram0 ro measureroot rootimg=rootfs.img rootimgpcr=9 console=tty0 console=ttyS0,115200n8"

inherit measured-bootimg
