DESCRIPTION = "Image with TPM support."

IMAGE_FEATURES += "ssh-server-openssh read-only-rootfs"

LICENSE = "MIT"

IMAGE_INSTALL += "\
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-boot \
	${@bb.utils.contains('MACHINE_FEATURES', 'tpm',   'packagegroup-tpm', '', d)} \
        ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2-crb', 'packagegroup-tpm2-crb', '', d)} \
        ${@bb.utils.contains('MACHINE_FEATURES', 'tpm2-tis', 'packagegroup-tpm2-tis', '', d)} \
	kmod \
	udev-extraconf \
"

inherit core-image
