FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Enable selinux support in the kernel if the feature is enabled
SRC_URI += "${@base_contains('DISTRO_FEATURES', 'tpm', 'file://tpm.cfg', '', d)}"
