package co.omise.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents Omise Token object.
 *
 * @see <a href="https://www.omise.co/tokens-api">Tokens API</a>
 */
public class Transfer extends Model {
    private String recipient;
    @JsonProperty("bank_account")
    private BankAccount bankAccount;
    private boolean sent;
    private boolean paid;
    private long fee;
    private long amount;
    private String currency;
    @JsonProperty("failure_code")
    private String failureCode;
    @JsonProperty("failure_message")
    private String failureMessage;
    private String transaction;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public static class Create extends Params {
        public Create amount(long amount) {
            add("amount", Long.toString(amount));
            return this;
        }

        public Create recipient(String recipient) {
            add("recipient", recipient);
            return this;
        }
    }

    public static class Update extends Params {
        private long amount;

        public Update amount(long amount) {
            add("amount", Long.toString(amount));
            return this;
        }
    }
}

