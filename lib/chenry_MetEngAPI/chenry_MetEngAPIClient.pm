package chenry_MetEngAPI::chenry_MetEngAPIClient;

use JSON::RPC::Legacy::Client;
use POSIX;
use strict;
use Data::Dumper;
use URI;
use Bio::KBase::Exceptions;
my $get_time = sub { time, 0 };
eval {
    require Time::HiRes;
    $get_time = sub { Time::HiRes::gettimeofday() };
};


# Client version should match Impl version
# This is a Semantic Version number,
# http://semver.org
our $VERSION = "0.1.0";

=head1 NAME

chenry_MetEngAPI::chenry_MetEngAPIClient

=head1 DESCRIPTION


A KBase module: chenry_MetEngAPI


=cut

sub new
{
    my($class, $url, @args) = @_;


    my $self = {
    client => chenry_MetEngAPI::chenry_MetEngAPIClient::RpcClient->new,
    url => $url,
    headers => [],
    };

    chomp($self->{hostname} = `hostname`);
    $self->{hostname} ||= 'unknown-host';

    #
    # Set up for propagating KBRPC_TAG and KBRPC_METADATA environment variables through
    # to invoked services. If these values are not set, we create a new tag
    # and a metadata field with basic information about the invoking script.
    #
    if ($ENV{KBRPC_TAG})
    {
    $self->{kbrpc_tag} = $ENV{KBRPC_TAG};
    }
    else
    {
    my ($t, $us) = &$get_time();
    $us = sprintf("%06d", $us);
    my $ts = strftime("%Y-%m-%dT%H:%M:%S.${us}Z", gmtime $t);
    $self->{kbrpc_tag} = "C:$0:$self->{hostname}:$$:$ts";
    }
    push(@{$self->{headers}}, 'Kbrpc-Tag', $self->{kbrpc_tag});

    if ($ENV{KBRPC_METADATA})
    {
    $self->{kbrpc_metadata} = $ENV{KBRPC_METADATA};
    push(@{$self->{headers}}, 'Kbrpc-Metadata', $self->{kbrpc_metadata});
    }

    if ($ENV{KBRPC_ERROR_DEST})
    {
    $self->{kbrpc_error_dest} = $ENV{KBRPC_ERROR_DEST};
    push(@{$self->{headers}}, 'Kbrpc-Errordest', $self->{kbrpc_error_dest});
    }


    my $ua = $self->{client}->ua;
    my $timeout = $ENV{CDMI_TIMEOUT} || (30 * 60);
    $ua->timeout($timeout);
    bless $self, $class;
    #    $self->_validate_version();
    return $self;
}




=head2 get_gene_table_from_model

  $output = $obj->get_gene_table_from_model($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.ModelOnlyInput
$output is a chenry_MetEngAPI.GeneTable
ModelOnlyInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
GeneTable is a reference to a hash where the following keys are defined:
    headings has a value which is a reference to a list where each element is a string
    data has a value which is a reference to a list where each element is a reference to a list where each element is a string

</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.ModelOnlyInput
$output is a chenry_MetEngAPI.GeneTable
ModelOnlyInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
GeneTable is a reference to a hash where the following keys are defined:
    headings has a value which is a reference to a list where each element is a string
    data has a value which is a reference to a list where each element is a reference to a list where each element is a string


=end text

=item Description

Retrieve master gene table

=back

=cut

 sub get_gene_table_from_model
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function get_gene_table_from_model (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to get_gene_table_from_model:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'get_gene_table_from_model');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.get_gene_table_from_model",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'get_gene_table_from_model',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_gene_table_from_model",
                        status_line => $self->{client}->status_line,
                        method_name => 'get_gene_table_from_model',
                       );
    }
}



=head2 get_model_data

  $output = $obj->get_model_data($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.ModelOnlyInput
$output is a chenry_MetEngAPI.GeneTable
ModelOnlyInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
GeneTable is a reference to a hash where the following keys are defined:
    headings has a value which is a reference to a list where each element is a string
    data has a value which is a reference to a list where each element is a reference to a list where each element is a string

</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.ModelOnlyInput
$output is a chenry_MetEngAPI.GeneTable
ModelOnlyInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
GeneTable is a reference to a hash where the following keys are defined:
    headings has a value which is a reference to a list where each element is a string
    data has a value which is a reference to a list where each element is a reference to a list where each element is a string


=end text

=item Description

Retrieve model data

=back

=cut

 sub get_model_data
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function get_model_data (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to get_model_data:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'get_model_data');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.get_model_data",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'get_model_data',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_model_data",
                        status_line => $self->{client}->status_line,
                        method_name => 'get_model_data',
                       );
    }
}



=head2 compute_biosynthesis_pathway

  $output = $obj->compute_biosynthesis_pathway($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.PathwayReactions
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
PathwayReactions is a reference to a hash where the following keys are defined:
    pathway_reactions has a value which is a reference to a list where each element is a chenry_MetEngAPI.PathwayReaction
    ATP_cost has a value which is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
PathwayReaction is a reference to a hash where the following keys are defined:
    id has a value which is a string
    intermediate has a value which is an int
    flux has a value which is a float
    max_flux has a value which is a float
    min_flux has a value which is a float

</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.PathwayReactions
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
PathwayReactions is a reference to a hash where the following keys are defined:
    pathway_reactions has a value which is a reference to a list where each element is a chenry_MetEngAPI.PathwayReaction
    ATP_cost has a value which is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
PathwayReaction is a reference to a hash where the following keys are defined:
    id has a value which is a string
    intermediate has a value which is an int
    flux has a value which is a float
    max_flux has a value which is a float
    min_flux has a value which is a float


=end text

=item Description

Compute the peripheral biosynthesis pathway for the selected target

=back

=cut

 sub compute_biosynthesis_pathway
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function compute_biosynthesis_pathway (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to compute_biosynthesis_pathway:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'compute_biosynthesis_pathway');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.compute_biosynthesis_pathway",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'compute_biosynthesis_pathway',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method compute_biosynthesis_pathway",
                        status_line => $self->{client}->status_line,
                        method_name => 'compute_biosynthesis_pathway',
                       );
    }
}



=head2 compute_competing_pathways

  $output = $obj->compute_competing_pathways($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.CompetingReactions
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
CompetingReactions is a reference to a hash where the following keys are defined:
    competing_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CompetingReactionData
CompetingReactionData is a reference to a hash where the following keys are defined:
    id has a value which is a string
    direction_for_competition has a value which is a string
    intermediate has a value which is an int
    flux has a value which is a float
    max_flux has a value which is a float
    min_flux has a value which is a float

</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.CompetingReactions
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
CompetingReactions is a reference to a hash where the following keys are defined:
    competing_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CompetingReactionData
CompetingReactionData is a reference to a hash where the following keys are defined:
    id has a value which is a string
    direction_for_competition has a value which is a string
    intermediate has a value which is an int
    flux has a value which is a float
    max_flux has a value which is a float
    min_flux has a value which is a float


=end text

=item Description

Compute the peripheral biosynthesis pathway for the selected target

=back

=cut

 sub compute_competing_pathways
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function compute_competing_pathways (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to compute_competing_pathways:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'compute_competing_pathways');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.compute_competing_pathways",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'compute_competing_pathways',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method compute_competing_pathways",
                        status_line => $self->{client}->status_line,
                        method_name => 'compute_competing_pathways',
                       );
    }
}



=head2 compute_cofactor_consuming_pathways

  $output = $obj->compute_cofactor_consuming_pathways($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.CofactorReactions
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
CofactorReactions is a reference to a hash where the following keys are defined:
    cofactor_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CofactorReactionData
CofactorReactionData is a reference to a hash where the following keys are defined:
    id has a value which is a string
    direction_for_competition has a value which is a string
    flux has a value which is a float
    max_flux has a value which is a float
    min_flux has a value which is a float

</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.CofactorReactions
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
CofactorReactions is a reference to a hash where the following keys are defined:
    cofactor_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CofactorReactionData
CofactorReactionData is a reference to a hash where the following keys are defined:
    id has a value which is a string
    direction_for_competition has a value which is a string
    flux has a value which is a float
    max_flux has a value which is a float
    min_flux has a value which is a float


=end text

=item Description

Compute the peripheral biosynthesis pathway for the selected target

=back

=cut

 sub compute_cofactor_consuming_pathways
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function compute_cofactor_consuming_pathways (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to compute_cofactor_consuming_pathways:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'compute_cofactor_consuming_pathways');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.compute_cofactor_consuming_pathways",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'compute_cofactor_consuming_pathways',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method compute_cofactor_consuming_pathways",
                        status_line => $self->{client}->status_line,
                        method_name => 'compute_cofactor_consuming_pathways',
                       );
    }
}



=head2 systematic_target_search

  $output = $obj->systematic_target_search($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.TargetModifications
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
TargetModifications is a reference to a hash where the following keys are defined:
    ko_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
        0: a string
        1: a float

    induction_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
        0: a string
        1: a float


</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.TargetModifications
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
TargetModifications is a reference to a hash where the following keys are defined:
    ko_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
        0: a string
        1: a float

    induction_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
        0: a string
        1: a float



=end text

=item Description

Systematically try all KO and return predicted production from each KO

=back

=cut

 sub systematic_target_search
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function systematic_target_search (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to systematic_target_search:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'systematic_target_search');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.systematic_target_search",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'systematic_target_search',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method systematic_target_search",
                        status_line => $self->{client}->status_line,
                        method_name => 'systematic_target_search',
                       );
    }
}



=head2 compute_flux

  $output = $obj->compute_flux($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.FluxData
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
FluxData is a reference to a hash where the following keys are defined:
    reaction_fluxes has a value which is a reference to a hash where the key is a string and the value is a float
    metabolite_flux has a value which is a reference to a hash where the key is a string and the value is a float

</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.ModelInput
$output is a chenry_MetEngAPI.FluxData
ModelInput is a reference to a hash where the following keys are defined:
    workspace has a value which is a string
    model has a value which is a string
    carbon_source has a value which is a string
    target has a value which is a string
    base_media has a value which is a string
    media_workspace has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a hash where the key is a string and the value is a float
    inductions has a value which is a reference to a hash where the key is a string and the value is a float
    cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float
FluxData is a reference to a hash where the following keys are defined:
    reaction_fluxes has a value which is a reference to a hash where the key is a string and the value is a float
    metabolite_flux has a value which is a reference to a hash where the key is a string and the value is a float


=end text

=item Description

Compute the peripheral biosynthesis pathway for the selected target

=back

=cut

 sub compute_flux
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function compute_flux (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to compute_flux:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'compute_flux');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.compute_flux",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'compute_flux',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method compute_flux",
                        status_line => $self->{client}->status_line,
                        method_name => 'compute_flux',
                       );
    }
}



=head2 list_maps

  $output = $obj->list_maps($MapInput)

=over 4

=item Parameter and return types

=begin html

<pre>
$MapInput is a chenry_MetEngAPI.MapInput
$output is a chenry_MetEngAPI.MapList
MapInput is a reference to a hash where the following keys are defined:
    model has a value which is a string
MapList is a reference to a hash where the following keys are defined:
    maps has a value which is a reference to a list where each element is a chenry_MetEngAPI.MapData
MapData is a reference to a hash where the following keys are defined:
    id has a value which is a string
    name has a value which is a string
    reactions has a value which is a reference to a list where each element is a string
    compounds has a value which is a reference to a list where each element is a string
    genes has a value which is a reference to a list where each element is a string
    total_reactions has a value which is an int
    total_compounds has a value which is an int

</pre>

=end html

=begin text

$MapInput is a chenry_MetEngAPI.MapInput
$output is a chenry_MetEngAPI.MapList
MapInput is a reference to a hash where the following keys are defined:
    model has a value which is a string
MapList is a reference to a hash where the following keys are defined:
    maps has a value which is a reference to a list where each element is a chenry_MetEngAPI.MapData
MapData is a reference to a hash where the following keys are defined:
    id has a value which is a string
    name has a value which is a string
    reactions has a value which is a reference to a list where each element is a string
    compounds has a value which is a reference to a list where each element is a string
    genes has a value which is a reference to a list where each element is a string
    total_reactions has a value which is an int
    total_compounds has a value which is an int


=end text

=item Description

List maps available for viewing

=back

=cut

 sub list_maps
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function list_maps (received $n, expecting 1)");
    }
    {
    my($MapInput) = @args;

    my @_bad_arguments;
        (ref($MapInput) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"MapInput\" (value was \"$MapInput\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to list_maps:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'list_maps');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.list_maps",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'list_maps',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method list_maps",
                        status_line => $self->{client}->status_line,
                        method_name => 'list_maps',
                       );
    }
}



=head2 get_map

  $output = $obj->get_map($params)

=over 4

=item Parameter and return types

=begin html

<pre>
$params is a chenry_MetEngAPI.EscherInput
$output is a chenry_MetEngAPI.EscherOutput
EscherInput is a reference to a hash where the following keys are defined:
    map_id has a value which is a string
    reaction_flux has a value which is a reference to a hash where the key is a string and the value is a float
    gene_expression has a value which is a reference to a hash where the key is a string and the value is a float
    metabolite_values has a value which is a reference to a hash where the key is a string and the value is a float
    model has a value which is a string
    target has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a list where each element is a string
    inductions has a value which is a reference to a list where each element is a string
EscherOutput is a reference to a hash where the following keys are defined:
    html has a value which is a string

</pre>

=end html

=begin text

$params is a chenry_MetEngAPI.EscherInput
$output is a chenry_MetEngAPI.EscherOutput
EscherInput is a reference to a hash where the following keys are defined:
    map_id has a value which is a string
    reaction_flux has a value which is a reference to a hash where the key is a string and the value is a float
    gene_expression has a value which is a reference to a hash where the key is a string and the value is a float
    metabolite_values has a value which is a reference to a hash where the key is a string and the value is a float
    model has a value which is a string
    target has a value which is a string
    kos has a value which is a reference to a list where each element is a string
    kds has a value which is a reference to a list where each element is a string
    inductions has a value which is a reference to a list where each element is a string
EscherOutput is a reference to a hash where the following keys are defined:
    html has a value which is a string


=end text

=item Description

Get an escher map painted with input data rendered in HTML format

=back

=cut

 sub get_map
{
    my($self, @args) = @_;

# Authentication: none

    if ((my $n = @args) != 1)
    {
    Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function get_map (received $n, expecting 1)");
    }
    {
    my($params) = @args;

    my @_bad_arguments;
        (ref($params) eq 'HASH') or push(@_bad_arguments, "Invalid type for argument 1 \"params\" (value was \"$params\")");
        if (@_bad_arguments) {
        my $msg = "Invalid arguments passed to get_map:\n" . join("", map { "\t$_\n" } @_bad_arguments);
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error => $msg,
                                   method_name => 'get_map');
    }
    }

    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.get_map",
        params => \@args,
    });
    if ($result) {
    if ($result->is_error) {
        Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'get_map',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
    } else {
        return wantarray ? @{$result->result} : $result->result->[0];
    }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method get_map",
                        status_line => $self->{client}->status_line,
                        method_name => 'get_map',
                       );
    }
}


sub status
{
    my($self, @args) = @_;
    if ((my $n = @args) != 0) {
        Bio::KBase::Exceptions::ArgumentValidationError->throw(error =>
                                   "Invalid argument count for function status (received $n, expecting 0)");
    }
    my $url = $self->{url};
    my $result = $self->{client}->call($url, $self->{headers}, {
        method => "chenry_MetEngAPI.status",
        params => \@args,
    });
    if ($result) {
        if ($result->is_error) {
            Bio::KBase::Exceptions::JSONRPC->throw(error => $result->error_message,
                           code => $result->content->{error}->{code},
                           method_name => 'status',
                           data => $result->content->{error}->{error} # JSON::RPC::ReturnObject only supports JSONRPC 1.1 or 1.O
                          );
        } else {
            return wantarray ? @{$result->result} : $result->result->[0];
        }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(error => "Error invoking method status",
                        status_line => $self->{client}->status_line,
                        method_name => 'status',
                       );
    }
}


sub version {
    my ($self) = @_;
    my $result = $self->{client}->call($self->{url}, $self->{headers}, {
        method => "chenry_MetEngAPI.version",
        params => [],
    });
    if ($result) {
        if ($result->is_error) {
            Bio::KBase::Exceptions::JSONRPC->throw(
                error => $result->error_message,
                code => $result->content->{code},
                method_name => 'get_map',
            );
        } else {
            return wantarray ? @{$result->result} : $result->result->[0];
        }
    } else {
        Bio::KBase::Exceptions::HTTP->throw(
            error => "Error invoking method get_map",
            status_line => $self->{client}->status_line,
            method_name => 'get_map',
        );
    }
}

sub _validate_version {
    my ($self) = @_;
    my $svr_version = $self->version();
    my $client_version = $VERSION;
    my ($cMajor, $cMinor) = split(/\./, $client_version);
    my ($sMajor, $sMinor) = split(/\./, $svr_version);
    if ($sMajor != $cMajor) {
        Bio::KBase::Exceptions::ClientServerIncompatible->throw(
            error => "Major version numbers differ.",
            server_version => $svr_version,
            client_version => $client_version
        );
    }
    if ($sMinor < $cMinor) {
        Bio::KBase::Exceptions::ClientServerIncompatible->throw(
            error => "Client minor version greater than Server minor version.",
            server_version => $svr_version,
            client_version => $client_version
        );
    }
    if ($sMinor > $cMinor) {
        warn "New client version available for chenry_MetEngAPI::chenry_MetEngAPIClient\n";
    }
    if ($sMajor == 0) {
        warn "chenry_MetEngAPI::chenry_MetEngAPIClient version is $svr_version. API subject to change.\n";
    }
}

=head1 TYPES



=head2 ModelOnlyInput

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
workspace has a value which is a string
model has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
workspace has a value which is a string
model has a value which is a string


=end text

=back



=head2 GeneTable

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
headings has a value which is a reference to a list where each element is a string
data has a value which is a reference to a list where each element is a reference to a list where each element is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
headings has a value which is a reference to a list where each element is a string
data has a value which is a reference to a list where each element is a reference to a list where each element is a string


=end text

=back



=head2 ModelData

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
reaction_headings has a value which is a reference to a list where each element is a string
reaction_data has a value which is a reference to a list where each element is a reference to a list where each element is a string
compound_headings has a value which is a reference to a list where each element is a string
compound_data has a value which is a reference to a list where each element is a reference to a list where each element is a string
transportable_compounds has a value which is a reference to a list where each element is a string
model_genes has a value which is a reference to a list where each element is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
reaction_headings has a value which is a reference to a list where each element is a string
reaction_data has a value which is a reference to a list where each element is a reference to a list where each element is a string
compound_headings has a value which is a reference to a list where each element is a string
compound_data has a value which is a reference to a list where each element is a reference to a list where each element is a string
transportable_compounds has a value which is a reference to a list where each element is a string
model_genes has a value which is a reference to a list where each element is a string


=end text

=back



=head2 ModelInput

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
workspace has a value which is a string
model has a value which is a string
carbon_source has a value which is a string
target has a value which is a string
base_media has a value which is a string
media_workspace has a value which is a string
kos has a value which is a reference to a list where each element is a string
kds has a value which is a reference to a hash where the key is a string and the value is a float
inductions has a value which is a reference to a hash where the key is a string and the value is a float
cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
workspace has a value which is a string
model has a value which is a string
carbon_source has a value which is a string
target has a value which is a string
base_media has a value which is a string
media_workspace has a value which is a string
kos has a value which is a reference to a list where each element is a string
kds has a value which is a reference to a hash where the key is a string and the value is a float
inductions has a value which is a reference to a hash where the key is a string and the value is a float
cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float


=end text

=back



=head2 PathwayReaction

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a string
intermediate has a value which is an int
flux has a value which is a float
max_flux has a value which is a float
min_flux has a value which is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a string
intermediate has a value which is an int
flux has a value which is a float
max_flux has a value which is a float
min_flux has a value which is a float


=end text

=back



=head2 PathwayReactions

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
pathway_reactions has a value which is a reference to a list where each element is a chenry_MetEngAPI.PathwayReaction
ATP_cost has a value which is a float
cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
pathway_reactions has a value which is a reference to a list where each element is a chenry_MetEngAPI.PathwayReaction
ATP_cost has a value which is a float
cofactor_stoichiometry has a value which is a reference to a hash where the key is a string and the value is a float


=end text

=back



=head2 CompetingReactionData

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a string
direction_for_competition has a value which is a string
intermediate has a value which is an int
flux has a value which is a float
max_flux has a value which is a float
min_flux has a value which is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a string
direction_for_competition has a value which is a string
intermediate has a value which is an int
flux has a value which is a float
max_flux has a value which is a float
min_flux has a value which is a float


=end text

=back



=head2 CompetingReactions

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
competing_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CompetingReactionData

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
competing_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CompetingReactionData


=end text

=back



=head2 CofactorReactionData

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a string
direction_for_competition has a value which is a string
flux has a value which is a float
max_flux has a value which is a float
min_flux has a value which is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a string
direction_for_competition has a value which is a string
flux has a value which is a float
max_flux has a value which is a float
min_flux has a value which is a float


=end text

=back



=head2 CofactorReactions

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
cofactor_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CofactorReactionData

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
cofactor_reactions has a value which is a reference to a hash where the key is a string and the value is a chenry_MetEngAPI.CofactorReactionData


=end text

=back



=head2 TargetModifications

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
ko_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
    0: a string
    1: a float

induction_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
    0: a string
    1: a float


</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
ko_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
    0: a string
    1: a float

induction_targets has a value which is a reference to a list where each element is a reference to a list containing 2 items:
    0: a string
    1: a float



=end text

=back



=head2 FluxData

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
reaction_fluxes has a value which is a reference to a hash where the key is a string and the value is a float
metabolite_flux has a value which is a reference to a hash where the key is a string and the value is a float

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
reaction_fluxes has a value which is a reference to a hash where the key is a string and the value is a float
metabolite_flux has a value which is a reference to a hash where the key is a string and the value is a float


=end text

=back



=head2 MapInput

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
model has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
model has a value which is a string


=end text

=back



=head2 MapData

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
id has a value which is a string
name has a value which is a string
reactions has a value which is a reference to a list where each element is a string
compounds has a value which is a reference to a list where each element is a string
genes has a value which is a reference to a list where each element is a string
total_reactions has a value which is an int
total_compounds has a value which is an int

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
id has a value which is a string
name has a value which is a string
reactions has a value which is a reference to a list where each element is a string
compounds has a value which is a reference to a list where each element is a string
genes has a value which is a reference to a list where each element is a string
total_reactions has a value which is an int
total_compounds has a value which is an int


=end text

=back



=head2 MapList

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
maps has a value which is a reference to a list where each element is a chenry_MetEngAPI.MapData

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
maps has a value which is a reference to a list where each element is a chenry_MetEngAPI.MapData


=end text

=back



=head2 EscherInput

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
map_id has a value which is a string
reaction_flux has a value which is a reference to a hash where the key is a string and the value is a float
gene_expression has a value which is a reference to a hash where the key is a string and the value is a float
metabolite_values has a value which is a reference to a hash where the key is a string and the value is a float
model has a value which is a string
target has a value which is a string
kos has a value which is a reference to a list where each element is a string
kds has a value which is a reference to a list where each element is a string
inductions has a value which is a reference to a list where each element is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
map_id has a value which is a string
reaction_flux has a value which is a reference to a hash where the key is a string and the value is a float
gene_expression has a value which is a reference to a hash where the key is a string and the value is a float
metabolite_values has a value which is a reference to a hash where the key is a string and the value is a float
model has a value which is a string
target has a value which is a string
kos has a value which is a reference to a list where each element is a string
kds has a value which is a reference to a list where each element is a string
inductions has a value which is a reference to a list where each element is a string


=end text

=back



=head2 EscherOutput

=over 4



=item Definition

=begin html

<pre>
a reference to a hash where the following keys are defined:
html has a value which is a string

</pre>

=end html

=begin text

a reference to a hash where the following keys are defined:
html has a value which is a string


=end text

=back



=cut

package chenry_MetEngAPI::chenry_MetEngAPIClient::RpcClient;
use base 'JSON::RPC::Client';
use POSIX;
use strict;

#
# Override JSON::RPC::Client::call because it doesn't handle error returns properly.
#

sub call {
    my ($self, $uri, $headers, $obj) = @_;
    my $result;


    {
    if ($uri =~ /\?/) {
        $result = $self->_get($uri);
    }
    else {
        Carp::croak "not hashref." unless (ref $obj eq 'HASH');
        $result = $self->_post($uri, $headers, $obj);
    }

    }

    my $service = $obj->{method} =~ /^system\./ if ( $obj );

    $self->status_line($result->status_line);

    if ($result->is_success) {

        return unless($result->content); # notification?

        if ($service) {
            return JSON::RPC::ServiceObject->new($result, $self->json);
        }

        return JSON::RPC::ReturnObject->new($result, $self->json);
    }
    elsif ($result->content_type eq 'application/json')
    {
        return JSON::RPC::ReturnObject->new($result, $self->json);
    }
    else {
        return;
    }
}


sub _post {
    my ($self, $uri, $headers, $obj) = @_;
    my $json = $self->json;

    $obj->{version} ||= $self->{version} || '1.1';

    if ($obj->{version} eq '1.0') {
        delete $obj->{version};
        if (exists $obj->{id}) {
            $self->id($obj->{id}) if ($obj->{id}); # if undef, it is notification.
        }
        else {
            $obj->{id} = $self->id || ($self->id('JSON::RPC::Client'));
        }
    }
    else {
        # $obj->{id} = $self->id if (defined $self->id);
    # Assign a random number to the id if one hasn't been set
    $obj->{id} = (defined $self->id) ? $self->id : substr(rand(),2);
    }

    my $content = $json->encode($obj);

    $self->ua->post(
        $uri,
        Content_Type   => $self->{content_type},
        Content        => $content,
        Accept         => 'application/json',
    @$headers,
    ($self->{token} ? (Authorization => $self->{token}) : ()),
    );
}



1;
