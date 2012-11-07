package svm.logic.implementation.controller;

import svm.domain.abstraction.DomainFacade;
import svm.domain.abstraction.exception.DomainAttributeException;
import svm.domain.abstraction.exception.DomainParameterCheckException;
import svm.domain.abstraction.modelInterfaces.ILocation;
import svm.domain.abstraction.modelInterfaces.IMember;
import svm.domain.abstraction.modelInterfaces.IUserPrivilege;
import svm.logic.abstraction.controller.IMemberController;
import svm.logic.abstraction.exception.IllegalGetInstanceException;
import svm.logic.abstraction.exception.NotAllowException;
import svm.logic.abstraction.transferobjects.*;
import svm.logic.implementation.tranferobjects.TransferMember;
import svm.logic.implementation.transferobjectcreator.TransferObjectCreator;
import svm.persistence.abstraction.exceptions.ExistingTransactionException;
import svm.persistence.abstraction.exceptions.NoSessionFoundException;
import svm.persistence.abstraction.exceptions.NoTransactionException;

import java.util.Date;

/**
 * Projectteam: Team C
 * Date: 02.11.12
 */
public class MemberController implements IMemberController {

    private IMember member;
    private Integer sessionId;
    private ITransferMember transferMember;
    private ITransferAuth user;

    public MemberController(IMember member, ITransferAuth user) {
        this.user = user;
        this.member = member;
    }


    @Override
    public ITransferMember getMember() {
        return this.transferMember;
    }

    @Override
    public void setTitle(String title) throws DomainAttributeException, NotAllowException {
        if (user.isAllowedForMemberChanging())
            this.member.setTitle(title);
        else {
            throw new NotAllowException("Wrong privilege");
        }
    }

    @Override
    public void setFirstName(String firstName) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setLastName(lastName);
    }

    @Override
    public void setSocialNumber(String socialNumber) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setSocialNumber(socialNumber);
    }

    @Override
    public void setBirthDate(Date birthDate) throws DomainParameterCheckException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setBirthDate(birthDate);
    }

    @Override
    public void setGender(String gender) throws DomainParameterCheckException, DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setGender(gender);
    }

    @Override
    public void setEntryDate(Date entryDate) throws DomainParameterCheckException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setEntryDate(entryDate);
    }

    @Override
    public void setPhone1(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setPhone1(val);
    }

    @Override
    public void setPhone2(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setPhone2(val);
    }

    @Override
    public void setEmail1(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setEmail1(val);
    }

    @Override
    public void setEmail2(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setEmail2(val);
    }

    @Override
    public void setFax(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setFax(val);
    }

    @Override
    public void setStreet(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setStreet(val);
    }

    @Override
    public void setStreetNumber(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setStreetNumber(val);
    }

    @Override
    public void setLat(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setCoordLat(val);
    }

    @Override
    public void setLong(String val) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.getContactDetails().setCoordLong(val);
    }

    @Override
    public void setLocation(ITransferLocation location) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        ILocation l = ((IHasModel<ILocation>) location).getModel();
        this.member.getContactDetails().setLocation(l);
    }

    @Override
    public void start() throws NoSessionFoundException, IllegalGetInstanceException {
        this.sessionId = DomainFacade.generateSessionId();
        DomainFacade.reattachObjectToSession(this.sessionId, member);

        this.transferMember = (ITransferMember) TransferObjectCreator.getInstance(TransferMember.class, member);
    }

    @Override
    public void commit() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.getMemberModelDAO().saveOrUpdate(sessionId, member);
        DomainFacade.commitTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public void abort() throws ExistingTransactionException, NoSessionFoundException, NoTransactionException {
        DomainFacade.startTransaction(this.sessionId);
        DomainFacade.abortTransaction(this.sessionId);
        DomainFacade.closeSession(this.sessionId);
    }

    @Override
    public void setPaidCurrentYear() throws NoSessionFoundException, InstantiationException, IllegalAccessException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setPaidCurrentYear();
    }

    @Override
    public void setUsername(String name) throws DomainAttributeException, NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");
        this.member.setUserName(name);
    }

    @Override
    public void addPrivilege(ITransferUserPrivilege privilege) throws NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");

        member.addPrivilege(((IHasModel<IUserPrivilege>) privilege).getModel());
    }

    @Override
    public void removePrivilege(ITransferUserPrivilege privilege) throws NotAllowException {
        if(!user.isAllowedForMemberChanging())
            throw new NotAllowException("Wrong privilege");

        member.removePrivilege(((IHasModel<IUserPrivilege>) privilege).getModel());
    }
}
