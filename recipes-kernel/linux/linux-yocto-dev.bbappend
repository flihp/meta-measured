require ${@bb.utils.contains_any('DISTRO_FEATURES', 'tpm tpm2 txt ima', 'linux-yocto-measured.inc', '', d)}

