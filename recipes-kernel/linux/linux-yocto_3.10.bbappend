FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Enable TPM support in the kernel if the feature is enabled.
#  Build modules for all the TPMs available.
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'tpm', 'file://tpm.cfg', '', d)}"
# Enable TXT support in the kernel if the feature is enabled.
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'txt', 'file://txt.cfg', '', d)}"
# Enable USB3
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'usb3', 'file://usb3.cfg', '', d)}"
