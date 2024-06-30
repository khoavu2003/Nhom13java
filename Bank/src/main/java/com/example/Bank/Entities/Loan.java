import com.example.Bank.Entities.bank_accounts;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id")
    private bank_accounts account;

    @NotNull
    @Column(name = "loan_amount", precision = 15, scale = 2)
    private BigDecimal loanAmount;

    @NotNull
    @Column(name = "interest_rate", precision = 5, scale = 2)
    private BigDecimal interestRate;

    @NotNull
    @Column(name = "loan_term")
    private Integer loanTerm;  // Term in months

    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @NotNull
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @NotNull
    @Column(name = "balance", precision = 15, scale = 2)
    private BigDecimal balance;

}
