import rbac.acl

if __name__ == "__main__":
    acl = rbac.acl.Registry()

    # add roles
    acl.add_role("user")
    acl.add_role("sys-admin", ["user"])
    acl.add_role("normal-user", ["user"])
    acl.add_role("limited-user", ["user"])
    acl.add_role("student", ["user"])

    # add resources
    acl.add_resource("file")
    acl.add_resource("network")
    acl.add_resource("system-file", ["file"])
    acl.add_resource("user-file", ["file"])
    acl.add_resource("student-drive")

    # set rules
    acl.deny("user", "edit", "system-file")
    acl.allow("sys-admin", "edit", "system-file")
    acl.allow("user", "access", "network")
    acl.deny("limited-user", "access", "network")
    acl.deny("user", "edit", "user-file")
    acl.allow("normal-user", "read", "user-file")
    acl.allow("limited-user", "edit", "user-file")
    acl.deny("user", "edit", "student-drive")
    acl.allow("student", "read", "student-drive")

    # check that it is correct
    print("sys-admin is {}allowed to edit system files".format(
        "not " if acl.is_allowed("sys-admin", "edit", "system-file")
        else ""))
    print("limited-user is {}allowed to edit system files".format(
        "not " if acl.is_allowed("limited-user", "edit", "system-file")
        else ""))
    print("student is {}allowed to read student drive".format(
        "not " if acl.is_allowed("student", "read", "student-drive")
        else ""))
    print("limited user is {}allowed to edit user files".format(
        "not " if acl.is_allowed("limited-user", "edit", "user-file")
        else ""))

