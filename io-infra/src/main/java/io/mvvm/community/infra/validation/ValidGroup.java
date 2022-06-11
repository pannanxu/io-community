package io.mvvm.community.infra.validation;

import javax.validation.groups.Default;

public interface ValidGroup extends Default {

    interface UpdateGroup extends ValidGroup {

    }

    interface QueryGroup extends ValidGroup {

    }

    interface DeleteGroup extends ValidGroup {

    }

    interface CreateGroup extends ValidGroup {

    }
}