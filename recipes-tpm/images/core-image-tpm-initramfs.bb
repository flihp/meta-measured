# Simple initramfs image based on core-image-minimal-initramfs.
#   Modified to include trousers daemon and tpm-tools.
DESCRIPTION = "Small image capable of booting a device. Trousers daemon and \
tpm-tools are included for measured launch of root files sytem."

IMAGE_INSTALL = " \
    initramfs-boot-tpm \
    busybox \
    udev \
    udev-extraconf \
    base-passwd \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm',  'packagegroup-tpm',  '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2', 'packagegroup-tpm2', '', d)} \
    coreutils \
    kmod \
    "

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "core-image-tpm-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

add_localhost () {
    echo "127.0.0.1	localhost" >> ${IMAGE_ROOTFS}/${sysconfdir}/hosts
}

ROOTFS_POSTPROCESS_COMMAND += "add_localhost;"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
