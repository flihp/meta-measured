DESCRIPTION = "Image with Trousers daemon." 

IMAGE_FEATURES += "ssh-server-openssh read-only-rootfs"

LICENSE = "MIT"

IMAGE_INSTALL += "\
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-boot \
	packagegroup-tpm \
	kmod \
	udev-extraconf \
	${@bb.utils.contains('MACHINE_FEATURES', 'tpm2', 'tpm2.0-tss', '', d)} \
"

inherit core-image
