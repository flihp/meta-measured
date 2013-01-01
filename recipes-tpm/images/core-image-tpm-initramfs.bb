# Simple initramfs image based on core-image-minimal-initramfs.
#   Modified to include trousers daemon and tpm-tools.
DESCRIPTION = "Small image capable of booting a device. Trousers daemon and \
tpm-tools are included for measured launch of root files sytem."

IMAGE_INSTALL = " \
    initramfs-boot-tpm \
    busybox \
    udev \
    base-passwd \
    packagegroup-tpm \
    "

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "core-image-tpm-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
ROOTFS_POSTPROCESS_COMMAND += "echo "127.0.0.1	localhost" > ${IMAGE_ROOTFS}/${sysconfdir}/hosts;"
inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
